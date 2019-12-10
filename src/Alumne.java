public class Alumne {

    private static int numAssignatures = 5;

    cLector teclat = new cLector();
    Gestor gestor = new Gestor();

    private String nom = "";
    private String cognoms = "";
    private String nif = "";
    private String matricula = "";
    private String dataNeix = "";
    private int progres = 0;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDataNeix() {
        return dataNeix;
    }

    public void setDataNeix(String dataNeix) {
        this.dataNeix = dataNeix;
    }

    public int getProgres() {
        return progres;
    }

    public void setProgres(int progres) {
        this.progres = progres;
    }

    protected void gestionarAlumne (Alumne alumne, Aula aula) throws InterruptedException {
        String nom = alumne.getNom();
        int posicio;
        String opcio;
        boolean cursAcabat = false;
        boolean fet = false;

        Assignatures assignatures = new Assignatures(numAssignatures);

        gestor.printGestorAlumne(nom);

        opcio = teclat.llegirString("Que vols fer? ").toLowerCase();

        while (!opcio.equals("0") && !opcio.equals("sortir") && !cursAcabat && !fet) {
            switch (opcio) {
                case "1":
                case "classe":
                    cursAcabat = escollirEstudi(alumne, assignatures);
                    fet = true;

                    if (cursAcabat) {
                        gestor.baixa(alumne, aula);
                        fet = false;
                    }
                    break;
                case "2":
                case "modificar":
                    alumne.modificarAlumne(alumne, aula.getAlumnes());
                    nom = alumne.getNom();
                    fet = true;
                    break;
                case "3":
                case "dades":
                    gestor.mostrarInfoAlumne(alumne);
                    fet = true;
                    break;
                case "4":
                case "baixa":
                    gestor.baixa(alumne, aula);
                    break;
                case "5":
                case "baixa alumne":
                    posicio = alumne.buscador(aula.getAlumnes());
                    try {
                        gestor.baixa(aula.getAlumnes()[posicio], aula);
                    } catch (Exception e) {
                        System.out.println("No hi ha alumnes creats");
                    }
                    break;
                default:
                    System.out.println("Això no és una opció");
                    System.out.println();
            }

            if (cursAcabat || fet) {
                gestor.printGestorAlumne(nom);
                opcio = teclat.llegirString("Que vols fer? ").toLowerCase();
                fet = false;
            }
            if (opcio.equals("5")) {
                fet = true;
            }
        }

        System.out.println("Acha luego");
    }

    protected boolean escollirEstudi(Alumne alumne, Assignatures assignatures) throws InterruptedException {
        gestor.estudiar(alumne.getNom());

        Assignatures[] assignaturesG;
        assignaturesG = assignatures.getAssignatures();

        String opcio;
        boolean valida = false;
        boolean cursAcabat = false;

        opcio = teclat.llegirString("Que vols estudiar? ").toLowerCase();

        while (!opcio.equals("0") && !opcio.equals("sortir") && !valida) {

            for (int i = 0; i < assignaturesG.length && !valida; i++) {
                if (opcio.equals(assignaturesG[i].getId()) || opcio.equals(assignaturesG[i].getNom()) || opcio.equals(assignaturesG[i].getNomGrm())) {
                    cursAcabat = gestor.progres(opcio, alumne);

                    valida = true;
                }
            }

            if (!valida) {
                System.out.println("Això no és una opció");
                System.out.println();
                opcio = teclat.llegirString("Que vols fer? ").toLowerCase();
            }
        }

        return cursAcabat;
    }

    protected boolean estudiar(int credits, String nomAssig, Alumne alumne) throws InterruptedException {

        int maxEstudi = 10;
        int estudiActual = alumne.getProgres();
        boolean cursAcabat;

        cursAcabat = gestor.afegirProgres(credits, estudiActual, maxEstudi, alumne, nomAssig);

        return cursAcabat;

    }

    protected int buscador(Alumne[] alumnes) {

        String aEditar = "";
        int alumne = 0;
        boolean seleccionat = false;
        boolean pintat = false;

        for (int i = 0; i < alumnes.length; i++) {
            if (alumnes[i] != null) {
                System.out.println("- " + alumnes[i].getNom() + " " + alumnes[i].getCognoms() + ": " + alumnes[i].getMatricula());
                pintat = true;
            }
        }
        System.out.println();

        if (!pintat) {
            seleccionat = true;
        } else {
            aEditar = teclat.llegirString("Posa la matrícula de l'alumne que vulguis tractar? ");
        }

        while (!seleccionat) {
            for (int i = 0; i < alumnes.length && !seleccionat; i++) {
                if (aEditar.equals(alumnes[i].getMatricula())) {
                    alumne = i;
                    seleccionat = true;
                }
            }
            if (!seleccionat) {
                System.out.println("Aquest alumne no existeix");
                aEditar = teclat.llegirString("Amb quin alumne vols tractar? ");
            }

        }

        return alumne;
    }

    protected void modificarAlumne(Alumne alumne, Alumne[] alumnes) {

        boolean existeix = false;
        String nom;
        String cognom;
        String string;

        System.out.println("Benvingut al procediment de modificació d'un alumnet");
        System.out.println("<==================================================>");
        System.out.println();
        System.out.println("Aqui pots canviar les dades de l'alumne");
        System.out.println();

        string = teclat.llegirString("Quin nif vols posar a l'alumne? ");

        for (int i = 0; i < alumnes.length; i++) {

            if (alumne.getNif().equals(string)) {
                System.out.println("Ja existeix algu amb aquest NIF.");
                System.out.println();
                existeix = true;
            }
        }

        if (!existeix) {
            nom = teclat.llegirString("Quin nom vols posar-l'hi? ");
            cognom = teclat.llegirString("Quin cognom vols posar-l'hi? ");
            alumne.setNom(nom);
            alumne.setCognoms(cognom);
            alumne.setNif(string);
        }
    }
}