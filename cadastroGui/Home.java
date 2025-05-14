import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Home extends Application {

    private Armazenagem armazenador = null;
    private String tipo = null;
    private String nomeArquivo = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home");

        Label lblTituloHome = new Label("Cadastro de Alunos Puc-SP");
        lblTituloHome.setId("titulo");

        ImageView imgLista = new ImageView();
        imgLista.setId("imagem");
        imgLista.setPreserveRatio(true);
        imgLista.setSmooth(true);
        imgLista.setFitWidth(75);
        imgLista.setFitHeight(75);
        try {
            Image image = new Image(getClass().getResourceAsStream("/resources/pucsp.png"));
            imgLista.setImage(image);
        } catch (Exception e) {
            imgLista.setImage(null);
        }
        
        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setId("botao");
        btnCadastrar.setOnAction(e -> {
            if(armazenador != null){
                new CadAlunos(armazenador);
            }
        });

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setId("botao");
        btnSalvar.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText(null);
            if(armazenador.getTamanho() == 0){
                showAlert("Não há alunos cadastrados para salvar.");
                return;
            }
            dialog.setContentText("Digite o nome do arquivo:");
            dialog.showAndWait().ifPresent(nome -> {
                nome = nome.trim();
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
        });

        Button btnRecuperar = new Button("Recuperar");
        btnRecuperar.setId("botao");
        btnRecuperar.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText(null);
            dialog.setContentText("Digite o nome do arquivo:");
            dialog.showAndWait().ifPresent(nome -> {
                nome = nome.trim();
                if (nome.isEmpty()) {
                    showAlert("Por favor informe um nome válido.");
                    return;
                }
                nome = InputException.validarNomeFile(nome);
                
                ArquivoTexto arquivo = new ArquivoTexto();
                if(arquivo.carregarTxt(armazenador, nome)){
                    showAlert("Alunos recuperados com sucesso!");
                }else{
                    showAlert("Erro ao recuperar " + nome + " talvez o arquivo não exista");
                }
                
            });
        });
        
        Button btnLista = new Button("Listar");
        btnLista.setId("botao");
        btnLista.setOnAction(e -> {
            new ListaAlunos(armazenador);
        });

        Button btnSair = new Button("Sair");
        btnSair.setId("botao");
        btnSair.setOnAction(e -> System.exit(0));

        VBox botoes = new VBox(btnCadastrar, btnSalvar,btnLista, btnRecuperar, btnSair);
        botoes.setId("botoes-v");

        HBox conteudo = new HBox(imgLista, botoes);
        conteudo.setId("conteudo");

        VBox painel = new VBox(lblTituloHome, conteudo);
        painel.setId("painel");

        Scene scene = new Scene(painel, 491, 420);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
        receberTipo();
    }

    private void showAlert(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    public void receberTipo(){
        ChoiceDialog<String> dialog = new ChoiceDialog<>("ArrayNormal", "ArrayNormal", "ArrayList");
        dialog.setTitle("Tipo de Armazenagem");
        dialog.setHeaderText(null);
        dialog.setContentText("Escolha o tipo de armazenagem:");
        dialog.showAndWait().ifPresent(tipo -> {
            if ("ArrayNormal".equals(tipo)) {
                    TextInputDialog inputDialog = new TextInputDialog();
                    inputDialog.setHeaderText(null);
                    inputDialog.setContentText("Digite a quantidade de alunos:");
                    inputDialog.showAndWait().ifPresent(inputQtd -> {
                        try {
                            int qtd = Integer.parseInt(inputQtd);
                            armazenador = new ArrayNormal(qtd);
                        } catch (NumberFormatException ex) {
                            showAlert("Quantidade inválida.");
                        }
                    });
                } else {
                    armazenador = new ListaArray();
                }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
