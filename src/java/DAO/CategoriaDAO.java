package DAO;

import modelo.Categoria;
import conexao.ConexaoBD;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoriaDAO implements InterfaceDAO<Categoria, Integer> {

    private Session session;

    @Override
    public boolean salvar(Categoria objeto) {
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
    public List<Categoria> findAll(){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT m FROM Categoria m ORDER BY nome ASC");

            List<Categoria> categorias = q.list();
  
            return categorias;
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
    public Categoria consultar(Integer chave) {
        session = ConexaoBD.getInstance();

        Categoria cat = null;
        try {
            cat = (Categoria)session.get(Categoria.class, chave); 
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return cat;
    }

    @Override
    public boolean alterar(Categoria objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Categoria cat = (Categoria)session.get(Categoria.class, objeto.getIdCategoria()); 
            cat.setNome(objeto.getNome());
            if(cat != null){
                transacao = session.beginTransaction();
                session.update(cat);
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
    public boolean excluir(Categoria objeto) {
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
