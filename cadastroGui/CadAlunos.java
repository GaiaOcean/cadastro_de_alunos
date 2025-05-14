import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CadAlunos extends Stage {

    private TextField txtNome, txtIdade, txtRg, txtRa, txtCurso, txtPeriodo;
    private Label lblSucesso;
    private Armazenagem armazenador;

    public CadAlunos(Armazenagem armazenador) {
        this.armazenador = armazenador;

        setTitle("Cadastro de Aluno");

        Label lblTitulo = new Label("CADASTRO DE ALUNO");
        lblTitulo.setId("titulo");

        lblSucesso = new Label("ALUNO CADASTRADO COM SUCESSO!");
        lblSucesso.setId("sucesso");
        lblSucesso.setVisible(false);

        VBox campos = new VBox(5);
        campos.setId("formulario");
        
        HBox t1 = new HBox(10);
        HBox t2 = new HBox(10);
        HBox t3 = new HBox(10);
        
        txtNome = addField(t1,"org-texto", "Nome:");
        txtIdade = addField(t1,"org-numP", "Idade:");
        txtRg = addField(t2,"org-numG", "RG:");
        txtRa = addField(t2,"org-numG", "RA:");
        txtCurso = addField(t3,"org-texto", "Curso:");
        txtPeriodo = addField(t3, "org-numP", "Período:");
        
        campos.getChildren().addAll(t1,t2,t3);

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setId("botao");
        btnCadastrar.setOnAction(e -> cadastrarAluno());

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setId("botao");
        btnCancelar.setOnAction(e -> cancelar());

        HBox botoes = new HBox(20, btnCadastrar, btnCancelar);
        botoes.setId("botoes");

        VBox root = new VBox(20, lblTitulo, lblSucesso, campos, botoes);
        root.setId("painel");

        Scene scene = new Scene(root, 500, 460);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());

        setScene(scene);
        show();
    }

    private TextField addField(HBox parent,String id, String labelText) {
        Label label = new Label(labelText);
        label.getStyleClass().add("label-form");

        TextField textField = new TextField();
        textField.getStyleClass().add("campo-form");
        textField.setId(id);

        VBox grupo = new VBox(6);
        grupo.getChildren().addAll(label,textField);
        
        parent.getChildren().add(grupo);
        return textField;
    }

    private void cadastrarAluno() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String rg = InputException.validarNumeroString(txtRg.getText(), "RG");
            String ra = InputException.validarNumeroString(txtRa.getText(), "RA");
            String curso = txtCurso.getText();
            int periodo = Integer.parseInt(txtPeriodo.getText());

            Aluno aluno = new Aluno(nome, idade, rg, ra, curso, periodo);
            
            if(!armazenador.inserirAluno(aluno)){
                showAlert("Não há mais espaço para cadastro");
            }else{
                lblSucesso.setVisible(true);
            }
            
            txtNome.clear();
            txtIdade.clear();
            txtRg.clear();
            txtRa.clear();
            txtCurso.clear();
            txtPeriodo.clear();

            
        } catch (NumberFormatException e) {
            showAlert("Idade e Período devem ser números válidos.");
        } catch (InputException e) {
            showAlert(e.getMessage());
        }
    }

    private void cancelar() {
        if (armazenador != null && armazenador.getTamanho() > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salvar dados");
            alert.setContentText("Deseja salvar os dados antes de sair?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setHeaderText(null);
                    dialog.setContentText("Digite o nome do arquivo:");
                    dialog.showAndWait().ifPresent(nome -> {
                    nome  = nome.trim();
                    if (nome.isEmpty()) {
                        showAlert("Por favor informe um nome válido. de arquivo inválido.");
                        return;
                    }
                
                    nome = InputException.validarNomeFile(nome);
                    try{
                        ArquivoTexto arquivo = new ArquivoTexto();
                        arquivo.salvarTxt(armazenador, nome);
                        showAlert("Arquivo salvo com sucesso!");
                    }catch(Exception ex){
                        showAlert("Erro ao salvar o arquivo: " + ex.getMessage());
                    }
                    });
                    }
            });
        }

        close();
    }

    private void showAlert(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
