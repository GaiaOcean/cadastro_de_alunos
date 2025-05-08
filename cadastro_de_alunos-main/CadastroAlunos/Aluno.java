
/**
 * Classe para representar (criar) objetos Aluno. 
 * Utiliza heranca com a classe Pessoa (pai)
 * 
 * @author Julio Arakaki 
 * @version 20250326
 */
public class Aluno extends Pessoa {
    // Atributos/Variaveis de instancia
    String ra;
    String curso;
    int periodo;

    /**
     * Aluno Construtor da classe Aluno
     *
     * @param nome (String) nome do aluno
     * @param idade (int) idade do aluno
     * @param rg (String) identificacao do aluno
     * @param ra (String) ra do aluno
     * @param curso (String) curso do aluno
     * @param periodo (int) periodo do aluno
     */
    public Aluno(String nome, int idade, String rg, String ra, String curso, int periodo){
        super(nome, idade, rg); // Chama contrutor da classe pai (Pessoa)
        
        // Preenche dados do aluno
        setRa(ra);
        setCurso(curso);
        setPeriodo(periodo);
    }
    public String getRg(){
        String rg = getId();
        return rg;
    }
    private void setRa(String ra){
        this.ra = ra;
    }

    private void setCurso(String curso){
        this.curso = curso;
    }

    private void setPeriodo(int periodo){
        this.periodo = periodo;
    }

    /**
     * Retorna o Ra do aluno
     *
     * @return (String) ra do aluno
     */
    public String getRa(){
        return(this.ra);
    }

    /**
     * Retorna o curso do aluno
     *
     * @return (String) curso do aluno
     */
    public String getCurso(){
        return(this.curso);
    }

    /**
     * Retorna o periodo do aluno
     *
     * @return (int) periodo do aluno
     */
    public int getPeriodo(){
        return(this.periodo);
    }

    /**
     *  Retorna dados do aluno
     *  @return (String) Dados de um aluno
     */
    public String toString(){
        String str = super.toString();
        str = str + "\nRa: " + getRa();
        str = str + "\nCurso: " + getCurso();
        str = str + "\nPeriodo: " + getPeriodo();
        return str;
    }
}

