package DAO;

import modelo.Qualis;
import conexao.ConexaoBD;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class QualisDAO implements InterfaceDAO<Qualis, Integer> {

    private Session session;

    @Override
    public boolean salvar(Qualis objeto) {
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
    public List<Qualis> findAll(){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT p FROM Qualis p ORDER BY classificacao ASC");

            List<Qualis> qualisL = q.list();
  
            return qualisL;
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
    public Qualis consultar(Integer chave) {
        session = ConexaoBD.getInstance();

        Qualis qua = null;
        try {
            qua = (Qualis)session.get(Qualis.class, chave); 
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return qua;
    }

    @Override
    public boolean alterar(Qualis objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Qualis qua = (Qualis)session.get(Qualis.class, objeto.getIdQualis());             
            qua.setClassificacao(objeto.getClassificacao());
            qua.setAno(objeto.getAno());
            qua.setIdPeriodico(objeto.getIdPeriodico());
            qua.setIdArea(objeto.getIdArea());
            
            if(qua != null){
                transacao = session.beginTransaction();
                session.update(qua);
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
    public boolean excluir(Qualis objeto) {
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
