public class Main {

    cLector teclat = new cLector();
    Escola escola = new Escola();
    Gestor gestor = new Gestor();
    private Escola[] escoles = new Escola[10];

    public Escola[] getEscoles() {
        return escoles;
    }

    public static void main(String[] args) {
        Main programa = new Main();
        programa.inici();
    }

    private void inici() {

        String opcio;
        int posicio;

        gestor.printInicial();
        opcio = teclat.llegirString("Que vols fer? ").toLowerCase();

        while (!opcio.equals("0") && !opcio.equals("sortir")) {
            switch (opcio) {
                case "1":
                case "crear":
                    posicio = gestor.gestorEntitats(escoles);
                    if (posicio < escoles.length) {
                        escoles[posicio] = new Escola();
                        escoles[posicio] = crearEscola(escoles[posicio], escoles);
                    }
                    break;
                case "2":
                case "gestionar":
                    posicio = escola.buscador(escoles);
                    try {
                        escoles[posicio].gestionarEscola(escoles[posicio], escoles);
                    } catch (Exception e) {
                        System.out.println("No hi ha escoles creades");
                    }

                    break;
                default:
                    System.out.println("Això no és una opció");
                    System.out.println();
            }
            gestor.printInicial();
            opcio = teclat.llegirString("Que vols fer? ").toLowerCase();
        }

        System.out.println("Acha luego");
    }

    protected Escola crearEscola(Escola escola, Escola[] escoles) {

        int aules;
        String nom;
        boolean creada = false;
        boolean existeix = false;

        System.out.println("Benvingut al pricediment de creació d'una escola");
        System.out.println("<==============================================>");
        System.out.println();
        /*System.out.println("Per sortir escriu 'sortir' al nom");
        System.out.println();*/

        nom = teclat.llegirString("Quin nom vols posar a l'escola? ").toLowerCase();

        /*if (!nom.equals("sortir")) {*/
        while (!creada) {

            for (int i = 0; !existeix && i < escoles.length; i++) {
                if (escoles[i] != null && escoles[i].getNom().equals(nom)) {
                    existeix = true;
                    System.out.println("Aquesta escola ja existeix D:");
                }
            }

            if (!existeix) {
                escola.setNom(nom);

                aules = teclat.llegirEnter("Quantes aules tindra l'escola? ");

                if (aules > escola.getMaxAules() || aules < 0) {
                    System.out.println("No pots crear tantes o tant poques aules.");
                } else {
                    escola.setNumAules(aules);
                    creada = true;
                }
            } else {
                nom = teclat.llegirString("Quin nom vols posar a l'escola? ").toLowerCase();
                existeix = false;
            }
        }

        /*}*/

        return escola;
    }
}

