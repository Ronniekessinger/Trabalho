package controlador;

import DAO.InterfaceDAO;
import DAO.CategoriaDAO;
import modelo.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class CategoriaController implements IControllerDAO<CategoriaController, Integer> {

    private Categoria categoria;

    public CategoriaController() {
        categoria = new Categoria();
    }

    private CategoriaController(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean salvar() {
        System.out.println(categoria);
        if(categoria.getIdCategoria() != null){
            return alterar();
        }
        InterfaceDAO<Categoria, Integer> categoriaDao = new CategoriaDAO();
        boolean salvou = categoriaDao.salvar(categoria);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg;
        if (salvou) {
            msg = new FacesMessage("Categoria cadastrada com sucesso");
            contexto.addMessage("form_cadastro_categoria", msg);
            categoria = new Categoria();
            return true;
        }
        msg = new FacesMessage("Não foi possível cadastrar a Categoria");
        contexto.addMessage("form_cadastro_categoria", msg);
        return false;

    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public ListDataModel<CategoriaController> listar() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<CategoriaController> categoriasController = new ArrayList();

        List<Categoria> categorias = categoriaDAO.findAll();

        if (categorias != null) {
            for (Categoria cat : categorias) {
                categoriasController.add(new CategoriaController(cat));
            }
            return new ListDataModel(categoriasController);
        }
        else {
            return null;
        }
    }

    public String alterarDados(Categoria categoria) {
        this.categoria = categoria;
        return "alterar_categoria";
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Categoria, Integer> categoriaDao = new CategoriaDAO();
        return categoriaDao.alterar(categoria);
    }

    @Override
    public String remover() {
        InterfaceDAO<Categoria, Integer> categoriaDao = new CategoriaDAO();
        if (categoriaDao.excluir(categoria)) {
            return "removeu_categoria";
        }
        else {
            return "nao_removeu_categoria";
        }
    }

    @Override
    public CategoriaController consultar(Integer chave) {
        InterfaceDAO<Categoria, Integer> categoriaDao = new CategoriaDAO();
        Categoria categoria = categoriaDao.consultar(chave);
        return new CategoriaController(categoria);
    }
}
