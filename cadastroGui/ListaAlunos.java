
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Escreva uma descrição da classe JavaFX ListaAlunos aqui.
 *
 * @author (seu nome aqui)
 * @version (número da versão ou data aqui)
 */
public class ListaAlunos extends Stage
{
    private Armazenagem armazenador;
    private TableView<Aluno> tabela;
    private ObservableList<Aluno> dados;

    public ListaAlunos(Armazenagem armazenador) {
        this.armazenador = armazenador;
        this.dados = FXCollections.observableArrayList();

        VBox root = new VBox(20);
        root.setId("painel");

        Label titulo = new Label("Lista de Alunos");
        titulo.setId("titulo");

        tabela = new TableView<>();
        tabela.setId("tabela");

        configurarColunas();

        Button btnAtualizar = new Button("Atualizar");
        btnAtualizar.setId("btn-atualizar");
        btnAtualizar.setOnAction(e -> atualizarDados());

        root.getChildren().addAll(titulo, tabela, btnAtualizar);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        setScene(scene);
        setTitle("Visualização de Alunos");
        show();

        atualizarDados();
    }

    private void configurarColunas() {
        TableColumn<Aluno, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Aluno, Integer> idadeCol = new TableColumn<>("Idade");
        idadeCol.setCellValueFactory(new PropertyValueFactory<>("idade"));

        TableColumn<Aluno, String> rgCol = new TableColumn<>("RG");
        rgCol.setCellValueFactory(new PropertyValueFactory<>("rg"));

        TableColumn<Aluno, String> raCol = new TableColumn<>("RA");
        raCol.setCellValueFactory(new PropertyValueFactory<>("ra"));

        TableColumn<Aluno, String> cursoCol = new TableColumn<>("Curso");
        cursoCol.setCellValueFactory(new PropertyValueFactory<>("curso"));

        TableColumn<Aluno, Integer> periodoCol = new TableColumn<>("Período");
        periodoCol.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        
        TableColumn<Aluno, Void> actionCol = new TableColumn<>("Ações");
        actionCol.setCellFactory(coluna -> new TableCell<>() {
            private final Button btn = new Button("Deletar");
        
            {
                btn.setId("btn-deletar");
                btn.setOnAction(e -> {
                    Aluno aluno = getTableView().getItems().get(getIndex());
                    String ra = aluno.getRa();
                    armazenador.removerAluno(ra);
                    atualizarDados();
                });
            }
        
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        tabela.getColumns().addAll(nomeCol, idadeCol, rgCol, raCol, cursoCol, periodoCol,actionCol);
    }

    private void atualizarDados() {
        dados.clear();
        for (int i = 0; i < armazenador.getTamanho(); i++) {
            dados.add(armazenador.getAluno(i));
        }
        tabela.setItems(dados);
    }
}
