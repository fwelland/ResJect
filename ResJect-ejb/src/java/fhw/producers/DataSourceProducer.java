package fhw.producers;

import fhw.qaulifiers.ListingDS;
import fhw.qaulifiers.UserDS;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@Default
public class DataSourceProducer
{
    
    @Resource(lookup = "Member")
    private DataSource userDS;        
    

    public DataSourceProducer()
    {
        System.err.println("DataSourceProducer.DataSourceProducer -- CONSTRUCTION"); 
    }

    @Produces
    @UserDS
    public DataSource getUserDataSource()
    {
        System.err.println("******DataSourceProducer.getUserDataSource; am I null?  " + (null == userDS) ) ;
        return userDS;

    }

    @Produces
    @ListingDS
    public DataSource getListingDataSource()
    {
        System.err.println("*******DataSourceProducer.getListingDataSource"); 
        return null; 
//        return listingsDS;
    }
}
