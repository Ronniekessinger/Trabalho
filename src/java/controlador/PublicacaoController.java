package controlador;

import DAO.InterfaceDAO;
import DAO.PublicacaoDAO;
import modelo.Publicacao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class PublicacaoController implements IControllerDAO<PublicacaoController, Integer> {

    private Publicacao publicacao;

    public PublicacaoController() {
        publicacao = new Publicacao();
    }

    private PublicacaoController(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public boolean salvar() {
        System.out.println(publicacao);
        if (publicacao.getIdPublicacao() != null) {
            InterfaceDAO publicacaoDao = new PublicacaoDAO();
            boolean salvou = publicacaoDao.salvar(publicacao);
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg;
            if (salvou) {
                msg = new FacesMessage("Publicação cadastrada com sucesso");
                contexto.addMessage("form_cadastro_publicacao", msg);
                publicacao = new Publicacao();
                return true;
            }
            msg = new FacesMessage("Não foi possível cadastrar a Pblicação");
            contexto.addMessage("form_cadastro_publicacao", msg);
            return false;
        }
        return false;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public ListDataModel<PublicacaoController> listar() {
        PublicacaoDAO publicacaoDAO = new PublicacaoDAO();
        List<PublicacaoController> publicacoesController = new ArrayList();

        List<Publicacao> publicacoes = publicacaoDAO.findAll();

        if (publicacoes != null) {
            for (Publicacao cat : publicacoes) {
                publicacoesController.add(new PublicacaoController(cat));
            }
            return new ListDataModel(publicacoesController);
        } else {
            return null;
        }
    }

    public String alterarDados(Publicacao publicacao) {
        this.publicacao = publicacao;
        return "alterar_publicacao";
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Publicacao, Integer> publicacaoDao = new PublicacaoDAO();
        return publicacaoDao.alterar(publicacao);
    }

    @Override
    public String remover() {
        InterfaceDAO<Publicacao, Integer> publicacaoDao = new PublicacaoDAO();
        if (publicacaoDao.excluir(publicacao)) {
            return "removeu_publicacao";
        } else {
            return "nao_removeu_publicacao";
        }
    }

    @Override
    public PublicacaoController consultar(Integer chave) {
        InterfaceDAO<Publicacao, Integer> publicacaoDao = new PublicacaoDAO();
        Publicacao publicacao = publicacaoDao.consultar(chave);
        return new PublicacaoController(publicacao);
    }

    List<PublicacaoController> buscar(Date dataInicial, Date dataFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
