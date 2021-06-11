package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;

public class Controller {

    @FXML
    private TextField txtA;

    @FXML
    private TextField txtB;

    @FXML
    private TextField txtC;

    @FXML
    private TextField txtD;

    @FXML
    private TextField txtPredator;

    @FXML
    private TextField txtPrey;

    @FXML
    private TextField txtSimulationTime;

    @FXML
    private TextArea txtWarning;

    @FXML
    private ScatterChart<Number, Number> graph;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    public Controller() {
        System.out.println("Calling constructor");
    }

    public void initialize() {
        System.out.println("Calling initialize");
    }

    @FXML
    void setBtnDrawPressed(ActionEvent event) {

        try {
            xAxis.setLabel("Liczebnosc populacji ofiar");
            yAxis.setLabel("Liczebnosc populacji drapieznikow");

            xAxis.setAutoRanging(true);
            yAxis.setAutoRanging(true);

            xAxis.setTickUnit(1);
            yAxis.setTickUnit(1);

            XYChart.Series<Number, Number> series1 = new XYChart.Series<>();

            for (int i = 0; i < getPhaseSpace()[0].length; i++) {
                series1.getData().add(new XYChart.Data<Number, Number>(getPhaseSpace()[0][i], getPhaseSpace()[1][i]));
            }
            graph.getData().addAll(series1);
            txtWarning.clear();
            txtWarning.appendText("Wprowadzone dane sa prawidlowe");
            txtWarning.setStyle("-fx-text-inner-color: green;");


        } catch (NumberFormatException e) {
            txtWarning.clear();
            txtWarning.appendText("Wprowadzone dane sa nieprawidlowe!");
            txtWarning.setStyle("-fx-text-inner-color: red;");
        }
    }

    @FXML
    void setBtnClearPressed(ActionEvent event) {

        graph.getData().removeAll(graph.getData());
        txtWarning.clear();
    }

    private double[][] getPhaseSpace() {
        double a = Double.parseDouble(String.valueOf(txtA.getText()));
        double b = Double.parseDouble(String.valueOf(txtB.getText()));
        double c = Double.parseDouble(String.valueOf(txtC.getText()));
        double d = Double.parseDouble(String.valueOf(txtD.getText()));
        double preys = Double.parseDouble(String.valueOf(txtPrey.getText()));
        double predators = Double.parseDouble(String.valueOf(txtPredator.getText()));
        double time = Double.parseDouble(String.valueOf(txtSimulationTime.getText()));

        if (a < 0.0 || b < 0.0 || c < 0.0 || d < 0.0 || preys < 0.0 || predators < 0.0 || time < 0.0) {
            throw new NumberFormatException("Wprowadzone wartości nie moga być ujemne");

        }

        FirstOrderDifferentialEquations lv = new LotkaVolterra(a, b, c, d);
        FirstOrderIntegrator eulerLv = new EulerIntegrator(0.01);
        LotkaVolterraPath lotkaVolterraPath = new LotkaVolterraPath();
        eulerLv.addStepHandler(lotkaVolterraPath);

        double[] yStart = new double[]{preys, predators};
        double[] yStop = new double[]{0, 0};

        eulerLv.integrate(lv, 0, yStart, time, yStop);

        double[] arrX = lotkaVolterraPath.ArrayList2Array(lotkaVolterraPath.getxValues());
        double[] arrY = lotkaVolterraPath.ArrayList2Array(lotkaVolterraPath.getyValues());
        double[] arrT = lotkaVolterraPath.ArrayList2Array(lotkaVolterraPath.gettValues());

        double[][] arr = {arrX, arrY, arrT};

        return arr;
    }
}
