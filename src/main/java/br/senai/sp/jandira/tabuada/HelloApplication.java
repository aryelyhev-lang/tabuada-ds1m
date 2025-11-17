package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    TextField textFieldMultiplicando;
    TextField textFieldMenorMultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {

        //definir tamanho da tela
        //stage.setWidth(500);
        stage.setHeight(600);
        stage.setTitle("Tabuada");

        //bloquear o redimencionamento da tela (aumentar e diminuir a janela)
        stage.setResizable(false);
        //se deixar o resizable como true o usúario ainda poderá mexer no tamanho da janela

        VBox root = new VBox();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #128181");

        //adcionar label ao header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 30;-fx-font-weight: Bold");
        Label labelSubtitulo = new Label("Construa tabuadas sem limites!");
        labelSubtitulo.setStyle("-fx-text-fill: #ffdde4;-fx-font-size: 15");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubtitulo);

        GridPane gridFormulario = new GridPane();
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(15);

        Label labelMultiplicando = new Label("Multiplicando");
        textFieldMultiplicando = new TextField();
        Label labelMenorMultiplicador = new Label("Menor Multiplicador");
        textFieldMenorMultiplicador = new TextField();
        Label labelMaiorMultiplicador = new Label("Maior Multiplicador");
        textFieldMaiorMultiplicador = new TextField();

        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(textFieldMultiplicando, 1, 0);
        gridFormulario.add(labelMenorMultiplicador, 0, 1);
        gridFormulario.add(textFieldMenorMultiplicador, 1, 1);
        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

        //criar os componentes de botões
        HBox boxBotoes = new HBox();
        boxBotoes.setAlignment(Pos.CENTER_RIGHT); //alinha a hbox no centro e na direita
        boxBotoes.setPadding(new Insets(0, 20, 20,10));
        boxBotoes.setSpacing(10);
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setPrefWidth(90); //mexe na largura
        btnCalcular.setOnAction(e -> {
            calcularTabuada();
        });

        Button btnLimpar= new Button("Limpar");
        btnLimpar.setPrefWidth(90);
        btnLimpar.setOnAction(e -> {
            limparFormulario();

        });

        Button btnSair = new Button("Sair");
        btnSair.setPrefWidth(90);
        btnSair.setOnAction(e -> {
            fechar();

        });

        //adicionar os botoes na boxBotoes
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        //adicionar o ListView
        listaTabuada = new ListView();

        //adicionar componente ListView
        VBox boxresultado = new VBox();
        boxresultado.setPadding(new Insets(0, 20, 20, 20));
        Label labelResultado = new Label("Resultado:");
        labelResultado.setStyle("-fx-text-fill: #3939a6;-fx-font-size: 18;-fx-font-weight: bold");

        //adicionar o label ao box resultados
        boxresultado.getChildren().add(labelResultado);
        boxresultado.getChildren().add(listaTabuada);

        //adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(boxBotoes);
        root.getChildren().add(boxresultado);
        stage.show();


    }

    public void fechar(){
        Alert alertaFechar = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Confirma a saida do sistema?",
                ButtonType.YES,
                ButtonType.NO
        );

        //mostrar e esperar, se for apenas "show" o sistema apenas execulta e não espera a resposta do usúario
        //o show retorna viod, ou seja, ele não retorna nada
        Optional<ButtonType> resposta = alertaFechar.showAndWait();

        if (resposta.isPresent() && resposta.get() == ButtonType.YES) {
            Platform.exit();
        }
    }

    public void limparFormulario(){
        textFieldMultiplicando.setText("");
        textFieldMenorMultiplicador.setText("");
        textFieldMaiorMultiplicador.setText("");
        listaTabuada.getItems().clear();
        //fazer o cursor voltar para a area de multiplicando sempre que o usúario limpar a tabuada
        textFieldMultiplicando.requestFocus();
    }

    public void calcularTabuada() {

        int multiplicando = Integer.parseInt(textFieldMultiplicando.getText());
        int menorMultiplicador = Integer.parseInt(textFieldMenorMultiplicador.getText());
        int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

        if(menorMultiplicador > maiorMultiplicador){
            int auxiliar = menorMultiplicador;
            menorMultiplicador = maiorMultiplicador;
            maiorMultiplicador = auxiliar;
        }

        int tamanho = maiorMultiplicador - menorMultiplicador + 1;
        String[] tabuada = new String[tamanho];

        int contador = 0;
        while (contador < tamanho) {

            double produto = multiplicando * menorMultiplicador;
            tabuada[contador] = multiplicando + " x " + menorMultiplicador + " = " + produto;
            contador++;
            menorMultiplicador++;

            }

           listaTabuada.getItems().clear();
           listaTabuada.getItems().addAll(tabuada);

        }


    }

