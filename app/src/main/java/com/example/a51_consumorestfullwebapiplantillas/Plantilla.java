package com.example.a51_consumorestfullwebapiplantillas;

public class Plantilla {

    private String idHospital;
    private String idSala;
    private String idEmpleado;
    private String apellido;
    private String funcion;
    private String turno;
    private String salario;

    public String getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(String idHospital) {
        this.idHospital = idHospital;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return  "\n"+
                "Plantilla : " +
                "idHospital='" + idHospital + '\'' +
                ", idSala='" + idSala + '\'' +
                ", idEmpleado='" + idEmpleado + '\'' +
                ", apellido='" + apellido + '\'' +
                ", funcion='" + funcion + '\'' +
                ", turno='" + turno + '\'' +
                ", salario='" + salario + '\'' +
                "\n"+
                " -----------------------------------------------\n";
    }
}