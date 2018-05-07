package controlador;

import DAO.InterfaceDAO;
import DAO.QualisDAO;
import modelo.Qualis;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class QualisController implements IControllerDAO<QualisController, Integer> {

    private Qualis qualis;

    public QualisController() {
        qualis = new Qualis();
    }

    private QualisController(Qualis qualis) {
        this.qualis = qualis;
    }

    @Override
    public boolean salvar() {
        System.out.println(qualis);
        if(qualis.getIdQualis() != null){
            return alterar();
        }
        InterfaceDAO qualisDao = new QualisDAO();
        boolean salvou = qualisDao.salvar(qualis);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg;
        if (salvou) {
            msg = new FacesMessage("Qualis cadastrado com sucesso");
            contexto.addMessage("form_cadastro_qualis", msg);
            qualis = new Qualis();
            return true;
        }
        msg = new FacesMessage("Não foi possível cadastrar o Qualis");
        contexto.addMessage("form_cadastro_qualis", msg);
        return false;

    }

    public Qualis getQualis() {
        return qualis;
    }

    public void setQualis(Qualis qualis) {
        this.qualis = qualis;
    }

    @Override
    public ListDataModel<QualisController> listar() {
        QualisDAO qualisDAO = new QualisDAO();
        List<QualisController> qualisLController = new ArrayList();

        List<Qualis> qualisL = qualisDAO.findAll();

        if (qualisL != null) {
            for (Qualis qua : qualisL) {
                qualisLController.add(new QualisController(qua));
            }
            return new ListDataModel(qualisLController);
        }
        else {
            return null;
        }
    }

    public String alterarDados(Qualis qualis) {
        this.qualis = qualis;
        return "alterar_qualis";
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Qualis, Integer> qualisDao = new QualisDAO();
        return qualisDao.alterar(qualis);
    }

    @Override
    public String remover() {
        InterfaceDAO<Qualis, Integer> qualisDao = new QualisDAO();
        if (qualisDao.excluir(qualis)) {
            return "removeu_qualis";
        }
        else {
            return "nao_removeu_qualis";
        }
    }
    
    @Override
    public QualisController consultar(Integer chave) {
        InterfaceDAO<Qualis, Integer> qualisDao = new QualisDAO();
        Qualis qualis = qualisDao.consultar(chave);
        return new QualisController(qualis);
    }
}
