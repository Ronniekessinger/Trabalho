package DAO;

import modelo.Autor;
import conexao.ConexaoBD;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AutorDAO implements InterfaceDAO<Autor, Integer> {

    private Session session;

    @Override
    public boolean salvar(Autor objeto) {
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
    public List<Autor> findAll(){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT e FROM Autor e ORDER BY nome ASC");

            List<Autor> autores = q.list();
  
            return autores;
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
    public Autor consultar(Integer chave) {
        session = ConexaoBD.getInstance();

        Autor aut = null;
        try {
            aut = (Autor)session.get(Autor.class, chave); 
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return aut;
    }

    @Override
    public boolean alterar(Autor objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Autor per = (Autor)session.get(Autor.class, objeto.getIdAutor()); 
            
            per.setNome(objeto.getNome());
            
            if(per != null){
                transacao = session.beginTransaction();
                session.update(per);
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
    public boolean excluir(Autor objeto) {
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
