package DAO;

import modelo.Publicacao;
import conexao.ConexaoBD;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PublicacaoDAO implements InterfaceDAO<Publicacao, Integer> {

    private Session session;

    @Override
    public boolean salvar(Publicacao objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            transacao = session.beginTransaction();
            session.saveOrUpdate(objeto);
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
    public List<Publicacao> findAll(){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT a FROM Publicacao a");

            List<Publicacao> publicacoes = q.list();
  
            return publicacoes;
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
    public Publicacao consultar(Integer objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean alterar(Publicacao objeto) {
        session = ConexaoBD.getInstance();
        Transaction transacao = null;

        try {
            Publicacao pub = (Publicacao)session.get(Publicacao.class, objeto.getIdPublicacao()); 
            pub.setAutor(objeto.getAutor());
            pub.setCategoria(objeto.getCategoria());
            pub.setArea(objeto.getArea());
            pub.setTitulo(objeto.getTitulo());
            pub.setPeriodico(objeto.getPeriodico());
            pub.setArquivo(objeto.getArquivo());
            pub.setAno(objeto.getAno());
            if(pub != null){
                transacao = session.beginTransaction();
                session.update(pub);
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
    public boolean excluir(Publicacao objeto) {
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
    
    public List<Publicacao> buscar(Date dataInicial, Date dataFinal){
        try{
            session = ConexaoBD.getInstance();
            Query q = session.createQuery("SELECT a FROM Publicacao a WHERE dataHora BETWEEN :dataInicial AND :dataFinal");
            System.out.println("dataInicial: " + dataInicial);
            q.setParameter("dataInicial", dataInicial);
            q.setParameter("dataFinal", dataFinal);

            List<Publicacao> publicacoes = q.list();
  
            return publicacoes;
        }
        catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        finally{
            session.close();
        }
    }
}
