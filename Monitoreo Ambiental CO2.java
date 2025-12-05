import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnvironmentalMonitoringSystem extends JFrame {
    private JLabel tempLabel, humidityLabel, co2Label, avgTempLabel, avgHumidityLabel, avgCo2Label, alertLabel;
    private JButton readSensorsButton, calculateAveragesButton, saveDataButton, loadDataButton, setThresholdsButton,
            generateReportButton;
    private JTextArea reportArea;
    private List<Double> tempData = new ArrayList<>();
    private List<Double> humidityData = new ArrayList<>();
    private List<Double> co2Data = new ArrayList<>();
    private double tempThreshold = 25.0;
    private double humidityThreshold = 60.0;
    private double co2Threshold = 1000.0;
    private Random random = new Random();

    public EnvironmentalMonitoringSystem() {
        setTitle("Sistema de Monitoreo Ambiental");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        tempLabel = new JLabel("Temperatura: -- °C");
        humidityLabel = new JLabel("Humedad: -- %");
        co2Label = new JLabel("CO2: -- ppm");
        avgTempLabel = new JLabel("Promedio Temp: -- °C");
        avgHumidityLabel = new JLabel("Promedio Humedad: -- %");
        avgCo2Label = new JLabel("Promedio CO2: -- ppm");
        alertLabel = new JLabel("Alertas: Ninguna");

        mainPanel.add(new JLabel("Lecturas Actuales:"));
        mainPanel.add(new JLabel(""));
        mainPanel.add(tempLabel);
        mainPanel.add(humidityLabel);
        mainPanel.add(co2Label);
        mainPanel.add(new JLabel(""));
        mainPanel.add(avgTempLabel);
        mainPanel.add(avgHumidityLabel);
        mainPanel.add(avgCo2Label);
        mainPanel.add(alertLabel);

        // Botones
        readSensorsButton = new JButton("Leer Sensores");
        calculateAveragesButton = new JButton("Calcular Promedios");
        saveDataButton = new JButton("Guardar Datos");
        loadDataButton = new JButton("Cargar Datos");
        setThresholdsButton = new JButton("Configurar Umbrales");
        generateReportButton = new JButton("Generar Reporte");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.add(readSensorsButton);
        buttonPanel.add(calculateAveragesButton);
        buttonPanel.add(saveDataButton);
        buttonPanel.add(loadDataButton);
        buttonPanel.add(setThresholdsButton);
        buttonPanel.add(generateReportButton);

        // Area de reporte
        reportArea = new JTextArea(10, 50);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        add(mainPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Action Listeners
        readSensorsButton.addActionListener(e -> readSensors());
        calculateAveragesButton.addActionListener(e -> calculateAverages());
        saveDataButton.addActionListener(e -> saveData());
        loadDataButton.addActionListener(e -> loadData());
        setThresholdsButton.addActionListener(e -> setThresholds());
        generateReportButton.addActionListener(e -> generateReport());

        // Estilo
        getContentPane().setBackground(new Color(240, 248, 255));
        mainPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBackground(new Color(240, 248, 255));
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        tempLabel.setFont(labelFont);
        humidityLabel.setFont(labelFont);
        co2Label.setFont(labelFont);
        avgTempLabel.setFont(labelFont);
        avgHumidityLabel.setFont(labelFont);
        avgCo2Label.setFont(labelFont);
        alertLabel.setFont(labelFont);
        alertLabel.setForeground(Color.RED);
    }

    // Funcion 1: Leer sensores (simula lecturas aleatorias)
    private void readSensors() {
        double temp = 20 + random.nextDouble() * 20; // 20-40°C
        double humidity = 30 + random.nextDouble() * 50; // 30-80%
        double co2 = 400 + random.nextDouble() * 1200; // 400-1600 ppm

        tempData.add(temp);
        humidityData.add(humidity);
        co2Data.add(co2);

        tempLabel.setText(String.format("Temperatura: %.2f °C", temp));
        humidityLabel.setText(String.format("Humedad: %.2f %%", humidity));
        co2Label.setText(String.format("CO₂: %.2f ppm", co2));

        checkAlerts(temp, humidity, co2);
    }

    // Funcion 2: Calcular promedios
    private void calculateAverages() {
        if (tempData.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para calcular promedios.");
            return;
        }
        double avgTemp = tempData.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double avgHumidity = humidityData.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double avgCo2 = co2Data.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        avgTempLabel.setText(String.format("Promedio Temp: %.2f °C", avgTemp));
        avgHumidityLabel.setText(String.format("Promedio Humedad: %.2f %%", avgHumidity));
        avgCo2Label.setText(String.format("Promedio CO2: %.2f ppm", avgCo2));
    }

    // Funcion 3: Guardar datos en archivo
    private void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("environmental_data.txt"))) {
            writer.println("Temperatura,Humedad,CO2");
            for (int i = 0; i < tempData.size(); i++) {
                writer.printf("%.2f,%.2f,%.2f%n", tempData.get(i), humidityData.get(i), co2Data.get(i));
            }
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " + e.getMessage());
        }
    }

    // Funcion 4: Cargar datos desde archivo
    private void loadData() {
        tempData.clear();
        humidityData.clear();
        co2Data.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("environmental_data.txt"))) {
            String line = reader.readLine(); // Saltar encabezado
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                tempData.add(Double.parseDouble(parts[0]));
                humidityData.add(Double.parseDouble(parts[1]));
                co2Data.add(Double.parseDouble(parts[2]));
            }
            JOptionPane.showMessageDialog(this, "Datos cargados exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }

    // Funcion 5: Configurar umbrales
    private void setThresholds() {
        String tempStr = JOptionPane.showInputDialog(this, "Umbral de Temperatura (°C):", tempThreshold);
        String humidityStr = JOptionPane.showInputDialog(this, "Umbral de Humedad (%):", humidityThreshold);
        String co2Str = JOptionPane.showInputDialog(this, "Umbral de CO2 (ppm):", co2Threshold);
        try {
            tempThreshold = Double.parseDouble(tempStr);
            humidityThreshold = Double.parseDouble(humidityStr);
            co2Threshold = Double.parseDouble(co2Str);
            JOptionPane.showMessageDialog(this, "Umbrales configurados.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.");
        }
    }

    // Funcion 6: Generar reporte
    private void generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Reporte de Monitoreo Ambiental\n");
        report.append("Lecturas totales: ").append(tempData.size()).append("\n");
        if (!tempData.isEmpty()) {
            double avgTemp = tempData.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double avgHumidity = humidityData.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double avgCo2 = co2Data.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            report.append(String.format("Promedio Temp: %.2f °C\n", avgTemp));
            report.append(String.format("Promedio Humedad: %.2f %%\n", avgHumidity));
            report.append(String.format("Promedio CO2: %.2f ppm\n", avgCo2));
        }
        reportArea.setText(report.toString());
    }

    // Funcion 7: Verificar alertas
    private void checkAlerts(double temp, double humidity, double co2) {
        StringBuilder alerts = new StringBuilder();
        if (temp > tempThreshold)
            alerts.append("Temperatura alta. ");
        if (humidity > humidityThreshold)
            alerts.append("Humedad alta. ");
        if (co2 > co2Threshold)
            alerts.append("CO2 alto. ");
        if (alerts.length() == 0)
            alerts.append("Ninguna");
        alertLabel.setText("Alertas: " + alerts.toString());
    }

    // Funcion 8: Actualizar interfaz (llamado implícitamente en readSensors)
    // Funcion 9: Limpiar datos (no implementada, pero podría agregarse)
    // Funcion 10: Mostrar estadísticas (integrada en generateReport)

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EnvironmentalMonitoringSystem().setVisible(true);
        });
    }
}
