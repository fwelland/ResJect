package fhw.bean;
import fhw.iface.ISimple;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


@Named(value = "userManager")
@RequestScoped
public class UserManager
{
    private String[] userList; 
    
    @EJB
    private ISimple simple; 

    /**
     * Creates a new instance of UserManager
     */
    public UserManager()
    {
    }

    
    private void loadUserList()
    {        
        userList = simple.getUserNames(20); 
    }
    
    public String[] getUserList()
    {
        if(null == userList)
            loadUserList();
        return userList;
    }

    public void setUserList(String[] userList)
    {
        this.userList = userList;
    }
    
    
    
}
