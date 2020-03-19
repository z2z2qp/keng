
@PersistenceContext
private EntityManager entityManager;
public void jdbcBatch(final List list){
    var session = (Session) entityManager.getDelegate();
    
    session.doWork(connection->{

        var sql = "";

        var stmt = connection.prepareStatment(sql);

        list.forEach(it->{
            try{
                stmt.setObject(1,it.value);
                stmt.addBatch;
            }catch(SQLException e){
                
            }
        });
        stmt.executeBatch();
        connection.commit();
    });
}