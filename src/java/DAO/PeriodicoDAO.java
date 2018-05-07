package DAO;

import modelo.Periodico;
import conexao.ConexaoBD;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PeriodicoDAO implements InterfaceDAO<Periodico, Integer> {

    private Session session;

    @Override
    public boolean salvar(Periodico objeto) {
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
    public List<Periodico> findAll(){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT p FROM Periodico p ORDER BY titulo ASC");

            List<Periodico> periodicos = q.list();
  
            return periodicos;
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
    public Periodico consultar(Integer chave) {
        session = ConexaoBD.getInstance();

        Periodico per = null;
        try {
            per = (Periodico)session.get(Periodico.class, chave); 
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        finally{
            session.close();
        }
        return per;
    }

    @Override
    public boolean alterar(Periodico objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Periodico per = (Periodico)session.get(Periodico.class, objeto.getIdPeriodico()); 
            
            per.setTitulo(objeto.getTitulo());
            per.setNacionalidade(objeto.getNacionalidade());
            per.setEndFoto(objeto.getEndFoto());
            per.setData(objeto.getData());
            per.setDescricao(objeto.getDescricao());
            per.setIssn(objeto.getIssn());
            
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
    public boolean excluir(Periodico objeto) {
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
