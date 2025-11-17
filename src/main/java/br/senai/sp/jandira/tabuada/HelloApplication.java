package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    TextField textFieldMultiplicando;
    TextField textFieldMenorMultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {

        //definir tamanho da tela
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle("Tabuada");

        VBox root = new VBox();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #c16ddc");

        //adcionar label ao header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 30;-fx-font-weight: Bold");
        Label labelSubtitulo = new Label("Construa tabuadas sem limites!");
        labelSubtitulo.setStyle("-fx-text-fill: #ffdde4;-fx-font-size: 15");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubtitulo);

        GridPane gridFormulario = new GridPane();

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
        //SETONACTION recebe uma funçao dentro de outra funçao
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setOnAction(e -> {
            calcularTabuada();
        });

        Button btnLimpar= new Button("Limpar");

        Button btnSair = new Button("Sair");

        //adicionar os botoes na boxBotoes
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        //adicionar o ListView
        listaTabuada = new ListView();


        //adicionar componente ListView
        VBox boxresultado = new VBox();
        Label labelResultado = new Label("Resultado:");
        labelResultado.setStyle("-fx-text-fill: blue;-fx-font-size: 18;-fx-font-weight: bold");

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

