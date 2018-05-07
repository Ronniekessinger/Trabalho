package controlador;

import DAO.InterfaceDAO;
import DAO.AutorDAO;
import modelo.Autor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class AutorController implements IControllerDAO<AutorController, Integer> {

    private Autor autor;

    public AutorController() {
        autor = new Autor();
    }

    private AutorController(Autor autor) {
        this.autor = autor;
    }

    @Override
    public boolean salvar() {
        System.out.println(autor);
        if(autor.getIdAutor() != null){
            return alterar();
        }
        InterfaceDAO<Autor, Integer> autorDao = new AutorDAO();
        boolean salvou = autorDao.salvar(autor);
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage msg;
        if (salvou) {
            msg = new FacesMessage("Autor cadastrado com sucesso");
            contexto.addMessage("form_cadastro_autor", msg);
            autor = new Autor();
            return true;
        }
        msg = new FacesMessage("Não foi possível cadastrar o Autor");
        contexto.addMessage("form_cadastro_autor", msg);
        return false;

    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public ListDataModel<AutorController> listar() {
        AutorDAO autorDAO = new AutorDAO();
        List<AutorController> autoresController = new ArrayList();

        List<Autor> autores = autorDAO.findAll();

        if (autores != null) {
            for (Autor cat : autores) {
                autoresController.add(new AutorController(cat));
            }
            return new ListDataModel(autoresController);
        } else {
            return null;
        }
    }

    public String alterarDados(Autor autor) {
        this.autor = autor;
        return "alterar_autor";
    }

    @Override
    public boolean alterar() {
        InterfaceDAO<Autor, Integer> autorDao = new AutorDAO();
        return autorDao.alterar(autor);
    }

    @Override
    public String remover() {
        InterfaceDAO<Autor, Integer> autorDao = new AutorDAO();
        if (autorDao.excluir(autor)) {
            return "removeu_autor";
        }
        else {
            return "nao_removeu_autor";
        }
    }
    
    @Override
    public AutorController consultar(Integer chave) {
        InterfaceDAO<Autor, Integer> autorDao = new AutorDAO();
        Autor autor = autorDao.consultar(chave);
        return new AutorController(autor);
    }
}
