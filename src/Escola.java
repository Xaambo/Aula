public class Escola {

    cLector teclat = new cLector();
    Gestor gestor = new Gestor();
    Aula aula = new Aula();

    private String nom = "";
    private int numAules;
    private Aula[] aules;
    private int maxAules = 2;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumAules() {
        return numAules;
    }

    public void setNumAules(int numAules) {
        this.numAules = numAules;
        aules = new Aula[numAules];
    }

    public Aula[] getAules() {
        return aules;
    }

    public void setAules(Aula[] aules) {
        this.aules = aules;
    }

    public int getMaxAules() {
        return maxAules;
    }

    protected void gestionarEscola(Escola escola, Escola[] escoles) throws InterruptedException {

        String nom = escola.getNom();
        int posicio;
        String opcio;

        escola.setAules(aules);

        gestor.printGestorEsc(nom);

        opcio = teclat.llegirString("Que vols fer? ").toLowerCase();

        while (!opcio.equals("0") && !opcio.equals("sortir")) {
            switch (opcio) {
                case "1":
                case "crear":
                    posicio = gestor.gestorEntitats(escola.aules);
                    if (posicio < escola.aules.length) {
                        escola.aules[posicio] = new Aula();
                        escola.aules[posicio] = crearAula(escola.aules[posicio], escola.aules);
                    }
                    break;
                case "2":
                case "modificar":
                    escola.modificarEscola(escola, escoles);
                    nom = escola.getNom();
                    break;
                case "3":
                case "info":
                    gestor.mostrarInfoEscola(escola);
                    break;
                case "4":
                case "gestionar":
                    posicio = aula.buscador(escola.aules);
                    try {
                        escola.aules[posicio].gestionarAula(escola.aules[posicio], escola);
                    } catch (Exception e) {
                        System.out.println("No hi ha aules creades");
                    }
                    break;
                default:
                    System.out.println("Això no és una opció");
                    System.out.println();
            }
        gestor.printGestorEsc(nom);
        opcio = teclat.llegirString("Que vols fer? ").toLowerCase();
        }

        System.out.println("Acha luego");
    }

    protected Aula crearAula(Aula aula, Aula[] aules) {

        int alumnes;
        String nom;
        boolean creada = false;
        boolean existeix = false;

        System.out.println("Benvingut al pricediment de creació d'una aula");
        System.out.println("<==============================================>");
        System.out.println();
        /*System.out.println("Per sortir escriu 'sortir' al nom");
        System.out.println();*/

        nom = teclat.llegirString("Quin nom vols posar a l'aula? ").toLowerCase();

        /*if (!nom.equals("sortir")) {*/
        while (!creada) {

            for (int i = 0; !existeix && i < aules.length; i++) {
                if (aules[i] != null && aules[i].getNom().equals(nom)) {
                    existeix = true;
                    System.out.println("Aquesta aula ja existeix D:");
                }
            }

            if (!existeix) {
                aula.setNom(nom);

                alumnes = teclat.llegirEnter("Quants alumnes tindra l'aula? ");

                if (alumnes > aula.getMaxAlumnes() || alumnes < 0) {
                    System.out.println("Només pot haver un maxim 20 alumnes per aula.");
                } else {
                    aula.setNumAlumnes(alumnes);
                    creada = true;
                }
            } else {
                nom = teclat.llegirString("Quin nom vols posar a l'aula? ").toLowerCase();
                existeix = false;
            }
        }

        /*}*/

        return aula;
    }

    protected int buscador(Escola[] escoles) {

        String aEditar = "";
        int escola = 0;
        boolean seleccionat = false;
        boolean pintat = false;

        for (int i = 0; i < escoles.length; i++) {
            if (escoles[i] != null) {
                System.out.println("- " + escoles[i].getNom());
                pintat = true;
            }
        }
        System.out.println();

        if (!pintat) {
            seleccionat = true;
        } else {
            aEditar = teclat.llegirString("Amb quina escola vols tractar? ");
        }

        while (!seleccionat) {
            for (int i = 0; i < escoles.length && !seleccionat; i++) {
                if (escoles[i] != null && aEditar.equals(escoles[i].getNom())) {
                    escola = i;
                    seleccionat = true;
                }
            }
            if (!seleccionat) {
                System.out.println("Aquesta escola no existeix");
                aEditar = teclat.llegirString("Amb quina escola vols tractar? ");
            }

        }

        return escola;
    }

    protected void modificarEscola(Escola escola, Escola[] escoles) {

        boolean existeix = false;
        String nom;

        System.out.println("Benvingut al procediment de modificació d'una escola");
        System.out.println("<==================================================>");
        System.out.println();
        System.out.println("Aqui pots canviar el nom de l'escola");

        nom = teclat.llegirString("Quin nom vols posar-l'hi? ");
        System.out.println();

        for (int i = 0; i < escoles.length && !existeix; i++) {
            if (escoles[i] != null && nom.equals(escoles[i].getNom())) {
                System.out.println("Aquesta escola ja existeix");
                existeix = true;
            }
        }

        if (!existeix) {
            escola.setNom(nom);
        }
    }
}
