package controlador;

import modelo.Autor;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

public class RelatorioController {

    private Date dataInicial;
    private Date dataFinal;

    public RelatorioController() {
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    
    
    public ListDataModel<PublicacaoController> listar(){
        PublicacaoController publicacaoController = new PublicacaoController();

        List<PublicacaoController> publicacoesController = publicacaoController.buscar(dataInicial, dataFinal);
        for (PublicacaoController publicacao : publicacoesController){
            Autor autor = publicacao.getPublicacao().getAutor();
        }

        if (publicacoesController != null){
            return new ListDataModel(publicacoesController);
        }
        else{
            return null;
        }
    }
    
    public String consultar(){
        if(dataInicial != null && dataFinal != null){
            return "consultar_relatorio";
        }
        else {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("Preencha os campos corretamente");
            contexto.addMessage("form_n_relatorio", msg);
            return null;
        }
    }
    
}
