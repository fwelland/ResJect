package fhw.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import fhw.iface.*;
import fhw.qaulifiers.UserDS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.inject.Inject;

@Stateless
@Remote(ISimple.class)
public class SimpleEJBImpl
        implements ISimple
{

    @Inject
    @UserDS
    private DataSource userDS;

    @Override
    public String[] getUserNames(Integer numUsers)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            conn = userDS.getConnection();
            pstmt = conn.prepareStatement("select FirstName, LastName from Member");
            rs = pstmt.executeQuery();
            List<String> l = new ArrayList<String>();
            while (rs.next())
            {
                String s = String.format("%s %s", rs.getString("FirstName"), rs.getString("LastName"));
                l.add(s);
            }
            String arr[] = new String[l.size()];
            l.toArray(arr);
            return (arr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e); 
        }
        finally
        {
            closeQuietly(rs, pstmt, conn);
        }
    }

    @Override
    public String[] getListingDescriptions(Integer numListigs)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void closeQuietly(ResultSet rs, Statement stmt, Connection conn)
    {
        closeQuietly(rs);
        closeQuietly(stmt, conn);
    }

    public void closeQuietly(Statement stmt, Connection conn)
    {
        closeQuietly(stmt);
        closeQuietly(conn);
    }

    public void closeQuietly(ResultSet rs)
    {
        if (null != rs)
        {
            try
            {
                rs.close();
            }
            catch (Throwable t)
            {
            }
        }
    }

    public void closeQuietly(Statement stmt)
    {
        if (null != stmt)
        {
            try
            {
                stmt.close();
            }
            catch (Throwable t)
            {
            }
        }
    }

    public void closeQuietly(Connection conn)
    {
        if (null != conn)
        {
            try
            {
                conn.close();
            }
            catch (Throwable t)
            {
            }
        }
    }
}
