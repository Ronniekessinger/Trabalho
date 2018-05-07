package DAO;

import modelo.Area;
import conexao.ConexaoBD;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AreaDAO implements InterfaceDAO<Area, Integer> {

    private Session session;

    @Override
    public boolean salvar(Area objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            transacao = session.beginTransaction();
            session.save(objeto);
            transacao.commit();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if(transacao.isActive()){
                transacao.rollback();
            }
        }
        finally{
            session.close();
        }
        return false;
    }
    
    @Override
    public List<Area> findAll(){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT p FROM Area p ORDER BY nome ASC");

            List<Area> areas = q.list();
  
            return areas;
        }
        catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        finally{
            session.close();
        }
    }

    @Override
    public Area consultar(Integer chave) {
        session = ConexaoBD.getInstance();

        Area are = null;
        try {
            are = (Area)session.get(Area.class, chave); 
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return are;
    }

    @Override
    public boolean alterar(Area objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Area are = (Area)session.get(Area.class, objeto.getIdArea()); 
            
            are.setNome(objeto.getNome());
            
            if(are != null){
                transacao = session.beginTransaction();
                session.update(are);
                transacao.commit();
                return true;
            }
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if(transacao.isActive()){
                transacao.rollback();
            }
        }
        finally{
            session.close();
        }
        return false;
    }

    @Override
    public boolean excluir(Area objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;
      
        try {
            transacao = session.beginTransaction();
            session.delete(objeto);
            transacao.commit();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if(transacao.isActive()){
                transacao.rollback();
            }
        }
        finally{
            session.close();
        }
        return false;
    }
}
