/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registroestudiantes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EasyList {

    StudentNodo cab;

    public EasyList() {
        cab = null;
    }

    public boolean getEmpty() {
        return cab == null;
    }

    public int getLenght() {
        if (getEmpty()) {
            return 0;
        } else {
            StudentNodo p = cab;
            int cont = 0;
            while (p != null) {
                cont++;
                p = p.sig;
            }
            return cont;
        }
    }

    public StudentNodo searchId(String dato) {
        if (getEmpty()) {
            return null;
        } else {
            StudentNodo p = cab;
            while (p != null) {
                if (p.id.equals(dato) || p.name.equalsIgnoreCase(dato)) {
                    return p;
                } else {
                    p = p.sig;
                }
            }
            return null;
        }
    }

    public StudentNodo nodeCreator(
            JTextField tfId,
            JTextField tfName,
            JTextField tfTutorName,
            JTextField tfTutorPhone,
            JComboBox cbGender,
            JSlider sLage,
            JSlider sLgrade
    ) {
        StudentNodo search = null;
        try {
            search = searchId(tfId.getText());
            if (search != null) {
                JOptionPane.showMessageDialog(null,
                        "El id ya se encuentra en la lista,"
                        + " por favor introduzca otro", "Error", JOptionPane.ERROR_MESSAGE);
                tfId.setText("");
                tfId.requestFocus();
                return null;
            }
            StudentNodo info = new StudentNodo(
                    tfId.getText(),
                    tfName.getText(),
                    cbGender.getSelectedItem().toString(),
                    tfTutorName.getText(),
                    tfTutorPhone.getText(),
                    sLage.getValue(),
                    sLgrade.getValue());
            tfId.setText("");
            tfName.setText("");
            tfTutorName.setText("");
            tfTutorPhone.setText("");
            cbGender.setSelectedIndex(0);
            sLage.setValue(7);
            sLgrade.setValue(1);
            JOptionPane.showMessageDialog(null, "Estudiante agregado exitosamente");
            return info;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e);
            return null;
        }
    }

    public void addToStart(
            JTextField tfId,
            JTextField tfName,
            JTextField tfTutorName,
            JTextField tfTutorPhone,
            JComboBox cbGender,
            JSlider sLage,
            JSlider sLgrade) {
        StudentNodo info = nodeCreator(
                tfId,
                tfName,
                tfTutorName,
                tfTutorPhone,
                cbGender,
                sLage,
                sLgrade
        );

        if (info != null) {
            if (getEmpty()) {
                cab = info;
            } else {
                info.sig = cab;
                cab = info;
            }
        }

    }

    public StudentNodo getEnd() {
        if (getEmpty()) {
            return null;
        } else {
            StudentNodo p = cab;
            while (p.sig != null) {
                p = p.sig;
            }
            return p;
        }
    }

    public void addToEnd(
            JTextField tfId,
            JTextField tfName,
            JTextField tfTutorName,
            JTextField tfTutorPhone,
            JComboBox cbGender,
            JSlider sLage,
            JSlider sLgrade) {
        StudentNodo info = nodeCreator(
                tfId,
                tfName,
                tfTutorName,
                tfTutorPhone,
                cbGender,
                sLage,
                sLgrade);
        if (info != null) {
            if (getEmpty()) {
                cab = info;
            } else {
                StudentNodo end = getEnd();
                end.sig = info;
            }
        }
    }

    public void registerRowJTable(DefaultTableModel studentModel,
            int Fila, StudentNodo info) {
        studentModel.setValueAt(info.id, Fila, 0);
        studentModel.setValueAt(info.name, Fila, 1);
        studentModel.setValueAt(info.age, Fila, 2);
        studentModel.setValueAt(info.grade, Fila, 3);
        studentModel.setValueAt(info.gender, Fila, 4);
        studentModel.setValueAt(info.tutorName, Fila, 5);
        studentModel.setValueAt(info.tutorPhone, Fila, 6);

    }

    public void fillJTable(JTable tab) {
        StudentNodo p = cab;
        int i = 0;
        DefaultTableModel studentModel = new DefaultTableModel();
        studentModel.addColumn("Id");
        studentModel.addColumn("Nombre");
        studentModel.addColumn("Edad");
        studentModel.addColumn("Grado");
        studentModel.addColumn("Genero");
        studentModel.addColumn("Nombre del tutor");
        studentModel.addColumn("Telefono del tutor");
        while (p != null) {
            studentModel.addRow(new Object[]{"", "", "", "", "", "", ""});
            registerRowJTable(studentModel, i, p);
            p = p.sig;
            i++;
        }
        tab.setModel(studentModel);
    }

    public void modifyData(
            String tfId,
            String tfName,
            String tfTutorName,
            String tfTutorPhone,
            String cbGender,
            int sLage,
            int sLgrade
    ) {
        if (getEmpty()) {

        } else {
            StudentNodo search = searchId(tfId);
            if (search == null) {
                JOptionPane.showMessageDialog(null,
                        "El código seleccionado no existe!");
            } else {
                try {
                    search.name = tfName;
                    search.tutorName = tfTutorName;
                    search.tutorPhone = tfTutorPhone;
                    search.gender = cbGender;
                    search.age = sLage;
                    search.grade = sLgrade;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "" + e);
                }
            }
        }
    }

    public StudentNodo getPrevious(StudentNodo current) {
        StudentNodo previous = null;
        if (getEmpty()) {
            return null;
        } else {
            previous = cab;
            while (previous.sig != current) {
                previous = previous.sig;
            }
            return previous;
        }
    }

    public void delStudent(String id) {
        if (getEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "La lista esta vacia");
        } else {
            StudentNodo p, previous = null;
            p = searchId(id);
            if (p == null) {
                JOptionPane.showMessageDialog(null,
                        "El id buscado no se encuentra"
                        + "verifique si lo ha escrito correctamente");
            } else if (p == cab && cab.sig == null) {
                cab = null;
                JOptionPane.showMessageDialog(null,
                        "La lista ha quedado vacia");
            } else if (p == cab && cab.sig != null) {
                cab = cab.sig;
                p = null;
                p.sig = null;
                JOptionPane.showMessageDialog(null,
                        "El elemento ha sido eliminado");
            } else if (p.sig == null) {
                previous = getPrevious(p);
                previous.sig = null;
                p = null;
                JOptionPane.showMessageDialog(null,
                        "El elemento ha sido eliminado");
            } else {
                previous = getPrevious(p);
                previous.sig = p.sig;
                p.sig = null;
                p = null;
                JOptionPane.showMessageDialog(null,
                        "El elemento ha sido eliminado");
            }
        }
    }

    public void addNextTo(
            JTextField tfId,
            JTextField tfName,
            JTextField tfTutorName,
            JTextField tfTutorPhone,
            JComboBox cbGender,
            JSlider sLage,
            String id,
            JSlider sLgrade) {
        StudentNodo info = nodeCreator(
                tfId,
                tfName,
                tfTutorName,
                tfTutorPhone,
                cbGender,
                sLage,
                sLgrade);
        StudentNodo p = null;
        p = searchId(id);
        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "La id no ha sido encontrada, verifique que la "
                    + "haya excrito correctamente");
        } else {
            if (p.sig == null) {
                p.sig = info;
            } else {
                info.sig = p.sig;
                p.sig = info;
            }
        }
    }

    public StudentNodo obtenerEstudianteMayorEdadPorGrado(int grado) {
        if (getEmpty()) {
            return null;
        } else {
            StudentNodo estudianteMayor = null;
            int mayorEdad = 0;
            StudentNodo p = cab;
            while (p != null) {
                if (p.grade == grado && p.age > mayorEdad) {
                    mayorEdad = p.age;
                    estudianteMayor = p;
                }
                p = p.sig;
            }
            return estudianteMayor;
        }
    }

    public List<StudentNodo> obtenerEstudiantesPorGradoYGenero(int grado, String genero) {
        List<StudentNodo> estudiantesFiltrados = new ArrayList<>();
        StudentNodo p = cab;
        while (p != null) {
            if (p.grade == grado && p.gender.equalsIgnoreCase(genero)) {
                estudiantesFiltrados.add(p);
            }
            p = p.sig;
        }
        return estudiantesFiltrados;
    }

    public int obtenerCantidadEstudiantesPorGradoYGenero(int grado, String genero) {
        int cantidad = 0;
        StudentNodo p = cab;
        while (p != null) {
            if (p.grade == grado && p.gender.equalsIgnoreCase(genero)) {
                cantidad++;
            }
            p = p.sig;
        }
        return cantidad;
    }

    public String generarInforme1() {
        StringBuilder informe = new StringBuilder();
        informe.append("Estudiantes de mayor edad en los cursos primero y quinto:\n\n");

        StudentNodo estudianteMayorPrimerGrado = obtenerEstudianteMayorEdadPorGrado(1);
        StudentNodo estudianteMayorQuintoGrado = obtenerEstudianteMayorEdadPorGrado(5);

        if (estudianteMayorPrimerGrado != null) {
            informe.append("Mayor edad en primer grado: ").append(estudianteMayorPrimerGrado.name)
                    .append(", Sexo: ").append(estudianteMayorPrimerGrado.gender).append("\n");
        }
        if (estudianteMayorQuintoGrado != null) {
            informe.append("Mayor edad en quinto grado: ").append(estudianteMayorQuintoGrado.name)
                    .append(", Sexo: ").append(estudianteMayorQuintoGrado.gender).append("\n");
        }

        return informe.toString();
    }

    public String generarInforme2() {
        StringBuilder informe = new StringBuilder();
        informe.append("Mujeres en quinto grado de primaria:\n\n");

        List<StudentNodo> mujeresQuintoGrado = obtenerEstudiantesPorGradoYGenero(5, "Femenino");
        int cantidadMujeres = mujeresQuintoGrado.size();

        double edadPromedio = 0;
        for (StudentNodo estudiante : mujeresQuintoGrado) {
            edadPromedio += estudiante.age;
        }
        if (cantidadMujeres > 0) {
            edadPromedio /= cantidadMujeres;
        }

        informe.append("Cantidad de mujeres: ").append(cantidadMujeres).append("\n");
        informe.append("Edad promedio: ").append(edadPromedio).append("\n");
        informe.append("Nombres de las mujeres:\n");
        for (StudentNodo estudiante : mujeresQuintoGrado) {
            informe.append("- ").append(estudiante.name).append("\n");
        }

        return informe.toString();
    }

    public String generarInforme3() {
        StringBuilder informe = new StringBuilder();
        informe.append("Ingreso al primer grado:\n\n");

        int cantidadMujeresPrimerGrado = obtenerCantidadEstudiantesPorGradoYGenero(1, "Femenino");
        int cantidadHombresPrimerGrado = obtenerCantidadEstudiantesPorGradoYGenero(1, "Masculino");

        informe.append("Cantidad de mujeres: ").append(cantidadMujeresPrimerGrado).append("\n");
        informe.append("Cantidad de hombres: ").append(cantidadHombresPrimerGrado).append("\n");

        return informe.toString();
    }
}
