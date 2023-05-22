package dao;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;
import model.Client;
/**
 * The Class AbstractDAO.
 *@author Bicioi Luiza
 * @param <T>
 *             generic type where T is a model class
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    private String createSelectAllQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }


    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + throwables.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T findByName(String nume) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query;
        if (this instanceof ClientDAO) query= createSelectQuery("nume");
        else query= createSelectQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, nume);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param t obiectul / tabelul
     * @param fields numele coloanelor
     * @return o parte din stringul pt query
     */
    public String getFieldsQueryString(T t,ArrayList<String> fields){
        StringBuilder sb = new StringBuilder();

        Field[] declaredFields = t.getClass().getDeclaredFields();
        for(Field field: declaredFields ){
            field.setAccessible(true);
            fields.add(field.getName());}
        for(int i = 0;i < fields.size();i++){

            if(i == fields.size()-1){
                sb.append(fields.get(i)+") ");
                break;
            }
            if(i!=0)sb.append(fields.get(i)+",");
        }
        return sb.toString();
    }

    /**
     *
     * @param t obiectul oferit/tabelul
     * @param fields ia numele coloanelor tabelei
     * @param values pune atatea semne de intrebare pe cate valori se afla in tabela
     * @return stringul pt continuarea query ului , adica values
     */
    public String getValuesQueryString(T t,ArrayList<String> fields,ArrayList<Object> values){
        Field[] declaredFields = t.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for(Field field: declaredFields ){
            field.setAccessible(true);
            try {
                Object value;
                value = field.get(t);
                values.add(value);
            } catch (IllegalAccessException | SecurityException | IllegalArgumentException e) {
                e.printStackTrace();
            }}
        for(int i = 0;i < fields.size();i++){

            if(i == fields.size()-1){
                sb.append("?);");
                break;
            }
            if(i!=0)sb.append("?,");
        }
        return sb.toString();
    }

    /**
     * Insereaza in tabela corespunzatoare din baza de date un obiect de tip T prin tehnica reflexiei
     * @param t obiectul dat
     */
    public void insert(T t) {
        ArrayList<Object> valori = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<String>();
        String insertQuery = "INSERT INTO " + type.getSimpleName() + " (";
        String fieldsForQuery = getFieldsQueryString(t,fields);
        String valuesForQuery = getValuesQueryString(t,fields,valori);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            insertQuery += fieldsForQuery + " VALUES(" + valuesForQuery;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertQuery);
            for(int i=1;i<fields.size();i++){
                if(valori.get(i) instanceof String){
                    statement.setString(i,valori.get(i).toString());
                }
                if(valori.get(i) instanceof Integer) {
                    statement.setInt(i, ((Integer) valori.get(i)).intValue());
                }
                if(valori.get(i) instanceof Double){
                    statement.setDouble(i,((Double) valori.get(i)).doubleValue());
                }
            }
            statement.execute();
        } catch (  SQLException|SecurityException | IllegalArgumentException   e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
    }

    /**
     * Cauta fieldurile tabelei respective exemplu: id, nume, emalil etc si in locul valorilor de tabele pune tot atatea semne de intrebare
     * @return returneaza stringul cu fieldurile respective plus semnele de intrebare
     */
    public String getFieldsForUpdateQuery(T t,ArrayList<String> fields,ArrayList<Object> values){
        StringBuilder sb = new StringBuilder();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for(Field field: declaredFields ){
            field.setAccessible(true);
            fields.add(field.getName());
            try {
                Object value;
                value = field.get(t);
                values.add(value);
            } catch (IllegalAccessException | SecurityException | IllegalArgumentException e) {
                e.printStackTrace();
            }}
        for(int i = 0;i < fields.size();i++){
            if(i == fields.size()-1){
                sb.append(fields.get(i)+" = ? ");
                break;
            }
            sb.append(fields.get(i)+" = ?, ");
        }
        return sb.toString();
    }

    /**
     * Modifica valorile tuplei corespunzatoare field-ului id din cadrul obiectului t.
     * @param t obiectul dat
     * @param id update ul se face dupa id ul oferit
     */
    public void update(T t, int id) {
        ArrayList<Object> valori = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<String>();
        String updateQuery = "UPDATE " + type.getSimpleName() + " SET ";
        Connection connection = null;
        PreparedStatement statement = null;
        String fieldsForQuery = getFieldsForUpdateQuery(t,fields,valori);
        updateQuery+=fieldsForQuery+ "WHERE id = ?;";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateQuery);
            for(int i=0;i<fields.size();i++){
                if(valori.get(i) instanceof String){statement.setString(i+1,valori.get(i).toString());}
                if(valori.get(i) instanceof Integer){statement.setInt(i+1,((Integer) valori.get(i)).intValue());}
                if(valori.get(i) instanceof Double){statement.setDouble(i+1,((Double) valori.get(i)).doubleValue());}
            }
            int n=fields.size()+1;
            statement.setInt(n, id);
            statement.executeUpdate();
        } catch (SQLException|SecurityException | IllegalArgumentException   e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
    }

    /**
     * apeleaza queryul din deleteQuery si astfel se face stergerea
     * @param id cel dupa care se face stergerea
     */
    public void delete(int id){
        Connection connection = null;
        String deleteQuery = "DELETE FROM " + type.getSimpleName() + " WHERE id  = ?;";
        PreparedStatement statement = null;
        T t = findById(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException|  SecurityException | IllegalArgumentException  e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }

    }
}
