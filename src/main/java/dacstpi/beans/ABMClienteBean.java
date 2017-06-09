package dacstpi.beans;

import dacstpi.dao.ClienteDao;
import dacstpi.dao.DaoException;
import dacstpi.model.Cliente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "abmclientebean")
@ViewScoped
public class ABMClienteBean {
    private Cliente cliente;
    @EJB
    private ClienteDao clienteDao;
    private List<Cliente> clienteList;


    @PostConstruct
    public void init(){
        try {
            this.cliente = new Cliente();
            clienteList = clienteDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> getClientes(){
        return clienteList;
    }

    public String updateCliente(){
        try {
            clienteDao.merge(this.cliente);
            clienteList = clienteDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return "ABMCliente.xhtml?faces-redirect=true";
    }

    public String agregarCliente(){
        try {
            clienteDao.persist(this.cliente);
            clienteList = clienteDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return "ABMCliente.xhtml?faces-redirect=true";
    }

    public String crearCliente(){
        this.cliente = new Cliente();
        return "createCliente.xhtml?faces-redirect=true";
    }

    public String removeCliente(Cliente c){
        try {
            clienteDao.remove(c);
            clienteList = clienteDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return "ABMCliente.xhtml?faces-redirect=true";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String editCliente(Cliente c){
        try {
            cliente = clienteDao.findById(c.getId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return "editCliente.xhtml?faces-redirect=true";
    }

}