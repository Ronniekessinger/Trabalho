package controlador;

import DAO.InterfaceDAO;
import DAO.AreaDAO;
import modelo.Area;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class AreaController implements IControllerDAO<AreaController, Integer> {

    private Area area;

    public AreaController() {
        area = new Area();
    }

    private AreaController(Area area) {
        this.area = area;
    }

    @Override
    public boolean salvar() {
        System.out.println(area);
        if(area.getIdArea() != null){
            return alterar();
        }
        InterfaceDAO areaDao = new AreaDAO();
        boolean salvou = areaDao.salvar(area);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg;
        if (salvou) {
            msg = new FacesMessage("Area cadastrada com sucesso");
            contexto.addMessage("form_cadastro_area", msg);
            area = new Area();
            return true;
        }
        msg = new FacesMessage("Não foi possível cadastrar a Area");
        contexto.addMessage("form_cadastro_area", msg);
        return false;

    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public ListDataModel<AreaController> listar() {
        AreaDAO areaDAO = new AreaDAO();
        List<AreaController> areasController = new ArrayList();

        List<Area> areas = areaDAO.findAll();

        if (areas != null) {
            for (Area are : areas) {
                areasController.add(new AreaController(are));
            }
            return new ListDataModel(areasController);
        }
        else {
            return null;
        }
    }

    public String alterarDados(Area area) {
        this.area = area;
        return "alterar_area";
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Area, Integer> areaDao = new AreaDAO();
        return areaDao.alterar(area);
    }

    @Override
    public String remover() {
        InterfaceDAO<Area, Integer> areaDao = new AreaDAO();
        if (areaDao.excluir(area)) {
            return "removeu_area";
        }
        else {
            return "nao_removeu_area";
        }
    }
    
    @Override
    public AreaController consultar(Integer chave) {
        InterfaceDAO<Area, Integer> areaDao = new AreaDAO();
        Area area = areaDao.consultar(chave);
        return new AreaController(area);
    }
}
