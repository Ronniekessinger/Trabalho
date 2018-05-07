package controlador;

import DAO.InterfaceDAO;
import DAO.PeriodicoDAO;
import modelo.Periodico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class PeriodicoController implements IControllerDAO<PeriodicoController, Integer> {

    private Periodico periodico;

    public PeriodicoController() {
        periodico = new Periodico();
    }

    private PeriodicoController(Periodico periodico) {
        this.periodico = periodico;
    }

    @Override
    public boolean salvar() {
        System.out.println(periodico);
        if(periodico.getIdPeriodico() != null){
            return alterar();
        }
        InterfaceDAO periodicoDao = new PeriodicoDAO();
        boolean salvou = periodicoDao.salvar(periodico);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg;
        if (salvou) {
            msg = new FacesMessage("Periódico cadastrado com sucesso");
            contexto.addMessage("form_cadastro_periodico", msg);
            periodico = new Periodico();
            return true;
        }
        msg = new FacesMessage("Não foi possível cadastrar o Periódico");
        contexto.addMessage("form_cadastro_periodico", msg);
        return false;

    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    @Override
    public ListDataModel<PeriodicoController> listar() {
        PeriodicoDAO periodicoDAO = new PeriodicoDAO();
        List<PeriodicoController> periodicosController = new ArrayList();

        List<Periodico> periodicos = periodicoDAO.findAll();

        if (periodicos != null) {
            for (Periodico per : periodicos) {
                periodicosController.add(new PeriodicoController(per));
            }
            return new ListDataModel(periodicosController);
        }
        else {
            return null;
        }
    }

    public String alterarDados(Periodico periodico) {
        this.periodico = periodico;
        return "alterar_periodico";
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Periodico, Integer> periodicoDao = new PeriodicoDAO();
        return periodicoDao.alterar(periodico);
    }

    @Override
    public String remover() {
        InterfaceDAO<Periodico, Integer> periodicoDao = new PeriodicoDAO();
        if (periodicoDao.excluir(periodico)) {
            return "removeu_periodico";
        }
        else {
            return "nao_removeu_periodico";
        }
    }
    
    @Override
    public PeriodicoController consultar(Integer chave) {
        InterfaceDAO<Periodico, Integer> periodicoDao = new PeriodicoDAO();
        Periodico periodico = periodicoDao.consultar(chave);
        return new PeriodicoController(periodico);
    }
}
