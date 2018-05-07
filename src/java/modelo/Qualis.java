package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "qualis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qualis.findAll", query = "SELECT a FROM Qualis a"),
    @NamedQuery(name = "Qualis.findByIdQualis", query = "SELECT a FROM Qualis a WHERE a.idQualis = :idQualis"),
    @NamedQuery(name = "Qualis.findByAno", query = "SELECT a FROM Qualis a WHERE a.ano = :ano"),
    @NamedQuery(name = "Qualis.findByIdPeriodico", query = "SELECT a FROM Qualis a WHERE a.idPeriodico = :idPeriodico"),
    @NamedQuery(name = "Qualis.findByIdArea", query = "SELECT a FROM Qualis a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Qualis.findByClassificacao", query = "SELECT a FROM Qualis a WHERE a.classificacao = :classificacao")})
public class Qualis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idQualis")
    private Integer idQualis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "classificacao")
    private String classificacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano")
    private String ano; 
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArea")
    private int idArea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPeriodico")
    private int idPeriodico;
    @JoinColumn(name = "idPeriodico", referencedColumnName = "idPeriodico", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodico periodico;
    @JoinColumn(name = "idArea", referencedColumnName = "idArea", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Area area;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "qualis")
    //private List<Publicacao> publicacaoList;

    public Qualis() {
    }

    public Qualis(Integer idQualis) {
        this.idQualis = idQualis;
    }

    public Qualis(Integer idQualis, String classificacao, String ano, Periodico idPeriodico, Area idArea) {
        this.idQualis = idQualis;
        this.classificacao = classificacao;
        this.ano = ano;
        this.periodico = idPeriodico;
        this.area = idArea;
    }

    public Integer getIdQualis() {
        return idQualis;
    }

    public void setIdQualis(Integer idQualis) {
        this.idQualis = idQualis;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Periodico getIdPeriodico() {
        return periodico;
    }

    public void setIdPeriodico(Periodico idPeriodico) {
        this.periodico = idPeriodico;
    }

    public Area getIdArea() {
        return area;
    }

    public void setIdArea(Area idArea) {
        this.area = idArea;
    }
/*
    @XmlTransient
    public List<Publicacao> getPublicacaoList() {
        return publicacaoList;
    }

    public void setPublicacaoList(List<Publicacao> publicacaoList) {
        this.publicacaoList = publicacaoList;
    }
    */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQualis != null ? idQualis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Qualis)) {
            return false;
        }
        Qualis other = (Qualis) object;
        if ((this.idQualis == null && other.idQualis != null) || (this.idQualis != null && !this.idQualis.equals(other.idQualis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Qualis[ idQualis=" + idQualis + " ]";
    }
    
}
