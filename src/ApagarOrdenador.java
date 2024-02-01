import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApagarOrdenador {

    public static void main(String[] args) {
        // Crear la ventana
        JFrame frame = new JFrame("Apagar Ordenador");

        // Configurar la operación predeterminada al cerrar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        // Crear una etiqueta con el mensaje y el tiempo restante
        JLabel label = new JLabel("El ordenador se apagará en 10 minutos.");
        panel.add(label);

        JLabel tiempoRestanteLabel = new JLabel();
        panel.add(tiempoRestanteLabel);

        // Crear un temporizador de cuenta regresiva
        Timer timer = new Timer(1000, new ActionListener() {
            int segundos = 600; // 10 minutos

            @Override
            public void actionPerformed(ActionEvent e) {
                segundos--;
                if (segundos <= 0) {
                    // Apagar el ordenador después de 10 minutos
                    apagarOrdenador();
                    ((Timer) e.getSource()).stop(); // Detener el temporizador
                } else {
                    // Actualizar la etiqueta con el tiempo restante
                    tiempoRestanteLabel.setText("Tiempo restante: " + formatearTiempo(segundos));
                }
            }
        });
        timer.start(); // Iniciar el temporizador

        // Configurar la ventana
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void apagarOrdenador() {
        // Crear el comando para apagar el ordenador (dependiendo del sistema operativo)
        String comandoApagado = (System.getProperty("os.name").toLowerCase().contains("win")) ? "shutdown -s -t 1" : "shutdown -h +10";

        try {
            // Ejecutar el comando de apagado
            Runtime.getRuntime().exec(comandoApagado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String formatearTiempo(int segundos) {
        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;
        return String.format("%02d:%02d", minutos, segundosRestantes);
    }
}
