import javax.swing.*;
/**
 * Write a description of class ArrayDIn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayNormal implements Armazenagem
{
    private Aluno[] lista;
    private int capacidade;
    private int cadastrados;
    
    
    public ArrayNormal(int capacidade){
       this.capacidade = capacidade;
       this.lista = new Aluno[capacidade];
       this.cadastrados = 0;
    }
    
    public  Aluno[] getAlunos(){
        int arraySize = getTamanho();
        Aluno[] alunos = new Aluno[arraySize];
        for (int i = 0; i <arraySize; i++) {
            alunos[i] = lista[i]; 
        }
        return  alunos;
    }
    
    public int getTamanho(){
        return cadastrados;
    }
    public void inserirAluno(Aluno aluno){
        if(capacidade == cadastrados){
            JOptionPane.showMessageDialog(null, "Não há mais espaço para cadastro");
        }else{
           lista[cadastrados] = aluno;
           cadastrados++;
           //System.out.println("Aluno " + aluno.getNome() + " cadastrado com sucesso.");
        }
    }
    public void removerAluno(String ra){
        int pos = buscarRa(ra);
        
        if(pos == -1){
            System.out.println("Ra " + ra + "não encontrado");
        }else{
            
            for(int i = pos; i < cadastrados-1; i++){
                lista[i] = lista[i+1];
            }
            cadastrados--;
        }
    }
    
    public int buscarRa(String ra){
        int pos = -1;
        int i = 0;
        while(pos == -1 && i < cadastrados){
            if(lista[i].getRa().equals(ra)){
                pos = i;
            }
            i++;
        }
        return pos;
    }
    
    public void mostrarLista(){
       String list = "";
       int nAluno = 0;
       
       if(cadastrados > 0){
           
         for(int i = 0;i < cadastrados;i++){

           if(lista[i] != null){
               nAluno++;
               list += "----------------- Aluno " + nAluno + "----------------\n";
               list += lista[i].toString()+ "\n\n";
           }

           }
            System.out.println(list); 
       }else{
            System.out.println("Não existem alunos cadastrados"); 
            return;
       }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

