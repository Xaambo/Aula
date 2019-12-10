import java.io.*;

public class Aula {

    private static int maxAlumnes = 20;

    cLector teclat = new cLector();
    Gestor gestor = new Gestor();
    Alumne alumne = new Alumne();

    private String nom = "";
    private int numAlumnes;
    private Alumne[] alumnes;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumAlumnes() {
        return numAlumnes;
    }

    public void setNumAlumnes(int numAlumnes) {
        this.numAlumnes = numAlumnes;
        alumnes = new Alumne[numAlumnes];
    }

    public int getMaxAlumnes() {
        return maxAlumnes;
    }

    public Alumne[] getAlumnes() {
        return alumnes;
    }

    public void setAlumnes(Alumne[] alumnes) {
        this.alumnes = alumnes;
    }

    protected void gestionarAula(Aula aula, Escola escola) throws InterruptedException {

        String nom = aula.getNom();
        int posicio;
        String opcio;

        aula.setAlumnes(alumnes);

        gestor.printGestorAula(nom);

        opcio = teclat.llegirString("Que vols fer? ").toLowerCase();

        while (!opcio.equals("0") && !opcio.equals("sortir")) {
            switch (opcio) {
                case "1":
                case "crear":
                    posicio = gestor.gestorEntitats(aula.alumnes);
                    if (posicio < aula.alumnes.length) {
                        aula.alumnes[posicio] = new Alumne();
                        alumnes[posicio] = crearAlumne(alumnes[posicio], aula);
                    }
                    break;
                case "2":
                case "modificar":
                    aula.modificarAula(aula, escola);
                    nom = aula.getNom();
                    break;
                case "3":
                case "gestionar":
                    posicio = alumne.buscador(alumnes);
                    try {
                        alumnes[posicio].gestionarAlumne(alumnes[posicio], aula);
                    } catch (Exception e) {
                        System.out.println("No hi ha alumnes creats");
                    }
                    break;
                default:
                    System.out.println("Això no és una opció");
                    System.out.println();
            }
            gestor.printGestorAula(nom);
            opcio = teclat.llegirString("Que vols fer? ").toLowerCase();
        }

        System.out.println("Acha luego");
    }

    protected Alumne crearAlumne(Alumne alumne, Aula aula) {

        String nom;
        String string;
        boolean creada = false, nif = false, matr = false, dataN = false, existeix = false;

        System.out.println("Benvingut al pricediment de creació d'una aula");
        System.out.println("<==============================================>");
        System.out.println();
        /*System.out.println("Per sortir escriu 'sortir' al nom");
        System.out.println();*/

        nom = teclat.llegirString("Nom de l'alumne? ").toLowerCase();

        /*if (!nom.equals("sortir")) {*/
        while (!creada) {

            for (int i = 0; !existeix && i < aula.getAlumnes().length; i++) {
                if (aula.getAlumnes()[i] != null && aula.getAlumnes()[i].getNom().equals(nom)) {
                    existeix = true;
                    System.out.println("Aquesta aula ja existeix D:");
                }
            }

            if (!existeix) {
                alumne.setNom(nom);

                alumne.setCognoms(teclat.llegirString("Cognoms? "));

                string = teclat.llegirString("NIF? ");

                while (!nif) {

                    if (string.length() == 9) {
                        alumne.setNif(string);
                        nif = true;

                        string = teclat.llegirString("Matrícula? ");

                        while (!matr) {

                            if (string.length() == 10) {
                                alumne.setMatricula(string);
                                matr = true;

                                string = teclat.llegirString("Data de neixement (dd/mm/yyyy)? ");

                                while (!dataN) {
                                    if (string.matches(".*.*/.*.*/.*.*.*.*")) {
                                        alumne.setDataNeix(string);
                                        dataN = true;

                                        creada = true;
                                    } else {
                                        System.out.println("La data de neixement ha de tenir format 'dd/mm/yyyy'.");

                                        string = teclat.llegirString("Data de neixement (dd/mm/yyyy)? ");
                                    }
                                }
                            } else {
                                System.out.println("La matricula ha de tenir 10 caracters.");

                                string = teclat.llegirString("Matrícula? ");
                            }
                        }
                    } else {
                        System.out.println("El NIF ha de tenir 9 caracters.");

                        string = teclat.llegirString("NIF? ");
                    }
                }
            } else {
                nom = teclat.llegirString("Nom de l'alumne? ").toLowerCase();
                existeix = false;
            }
        }

        /*}*/

        return alumne;
    }

    protected int buscador(Aula[] aules) {

        String aEditar = "";
        int aula = 0;
        boolean seleccionat = false;
        boolean pintat = false;

        for (int i = 0; i < aules.length; i++) {
            if (aules[i] != null) {
                System.out.println("- " + aules[i].getNom());
                pintat = true;
            }
        }
        System.out.println();

        if (!pintat) {
            seleccionat = true;
        } else {
            aEditar = teclat.llegirString("Amb quina aula vols tractar? ");
        }

        while (!seleccionat) {
            for (int i = 0; i < aules.length && !seleccionat; i++) {
                if (aEditar.equals(aules[i].getNom())) {
                    aula = i;
                    seleccionat = true;
                }
            }
            if (!seleccionat) {
                System.out.println("Aquesta aula no existeix");
                aEditar = teclat.llegirString("Amb quina aula vols tractar? ");
            }

        }

        return aula;
    }

    protected void modificarAula(Aula aula, Escola escola) {

        boolean existeix = false;
        String nom;

        Aula[] aules = escola.getAules();

        System.out.println("Benvingut al procediment de modificació d'una aula");
        System.out.println("<==================================================>");
        System.out.println();
        System.out.println("Aqui pots canviar el nom de l'aula");

        nom = teclat.llegirString("Quin nom vols posar-l'hi? ");
        System.out.println();

        for (int i = 0; i < aules.length && !existeix; i++) {
            if (aules[i] != null && nom.equals(aules[i].getNom())) {
                System.out.println("Aquesta aula ja existeix");
                existeix = true;
            }
        }

        if (!existeix) {
            aula.setNom(nom);
        }
    }
}
