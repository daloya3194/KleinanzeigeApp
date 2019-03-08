package impl;

import utils.DBUtil;
import utils.StoreException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseService implements Closeable {

    protected Connection connection;
    protected boolean complete;

    public BaseService() throws StoreException {
        try {
            connection = DBUtil.getConnection("insdb4");
            connection.setAutoCommit(false);
        }
        catch (SQLException e){
            throw new StoreException(e);
        }

    }


    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }

    }
}
