public class Gestor {

    /**PRINTS DELS GESTORS DE LES ENTITATS*/

    protected void printInicial() {

        System.out.println();
        System.out.println("Benvingut al gestor d'Escoles");
        System.out.println("<===========================>");
        System.out.println();
        System.out.println("1.- Crear una escola");
        System.out.println("2.- Gestionar una Escola");
        System.out.println("0.- Sortir");
        System.out.println();

    }

    protected void printGestorEsc(String nom) {

        System.out.println();
        System.out.println("Benvingut a la escola " + nom);
        System.out.println();
        System.out.println("1.- Crear una aula");
        System.out.println("2.- Modificar una escola");
        System.out.println("3.- Veure dades de l'escola");
        System.out.println("4.- Gestionar una aula");
        System.out.println("0.- Sortir");
        System.out.println();

    }

    protected void printGestorAula(String nom) {

        System.out.println();
        System.out.println("Benvingut a la aula " + nom);
        System.out.println();
        System.out.println("1.- Crear un alumne");
        System.out.println("2.- Modificar una aula");
        System.out.println("3.- Gestionar un alumne");
        System.out.println("0.- Sortir");
        System.out.println();

    }

    protected void printGestorAlumne(String nom) {

        System.out.println();
        System.out.println("Benvingut " + nom);
        System.out.println();
        System.out.println("1.- Anar a classe");
        System.out.println("2.- Modificar un alumne");
        System.out.println("3.- Veure dades de l'alumne");
        System.out.println("4.- Donar de baixa a " + nom);
        System.out.println("5.- Donar de baixa a un altre alumne");
        System.out.println("0.- Sortir");
        System.out.println();

    }

    protected void estudiar(String nom) {

        System.out.println();
        System.out.println("Benvingut a classe " + nom);
        System.out.println();
        System.out.println("Que vols estudiar? ");
        System.out.println("1- Programació (5 crèdits)");
        System.out.println("2- Física (4 crèdits)");
        System.out.println("3- Matemàtiques (3 crèdits)");
        System.out.println("4- EiE (2 crèdits)");
        System.out.println("5- Turoria (1 crèdits)");
        System.out.println();

    }

    /**GESTORIA DELS ALUMNES*/

    protected boolean progres(String opcio, Alumne alumne) throws InterruptedException {

        int credits;
        String nomAssig;
        boolean cursAcabat = false;

        switch (opcio) {
            case "1":
            case "programacio":
            case "programació":
                credits = 5;
                nomAssig = "Programació";

                cursAcabat = alumne.estudiar(credits, nomAssig, alumne);

                break;
            case "2":
            case "fisica":
            case "física":
                credits = 4;
                nomAssig = "Física";

                cursAcabat = alumne.estudiar(credits, nomAssig, alumne);

                break;
            case "3":
            case "matematiques":
            case "matemàtiques":
                credits = 3;
                nomAssig = "Matemàtiques";

                cursAcabat = alumne.estudiar(credits, nomAssig, alumne);

                break;
            case "4":
            case "eie":
                credits = 2;
                nomAssig = "Empresa i Emprenedoria";

                cursAcabat = alumne.estudiar(credits, nomAssig, alumne);

                break;
            case "5":
            case "tutoria":
                credits = 1;
                nomAssig = "Tutoria";

                cursAcabat = alumne.estudiar(credits, nomAssig, alumne);

                break;
        }

        return cursAcabat;
    }

    protected void baixa(Alumne alumne, Aula aula) {
        Alumne[] alumnes;
        boolean fet = false;

        alumnes = aula.getAlumnes();

        for (int i = 0; !fet && i < alumnes.length; i++) {
            if (alumne.getNif().equals(alumnes[i].getNif())) {
                alumnes[i] = null;
                aula.setAlumnes(alumnes);

                if (alumnes[i] == null) {
                    System.out.println();
                    System.out.println("Alumne donat de baixa de l'aula satisfactoriament");
                    fet = true;
                }
            }
        }
    }

    /**BUSCADOR DE LA OPTIMITZACIÓ*/

    protected int gestorEntitats(Object[] object) {

        int posicio = 0;
        boolean trobat = false;
        boolean full = false;

        for(int i = 0; i < object.length; i++){

            if (object[i] == null) {
                full = true;
            }
        }

        if (!full) {
            System.out.println();
            System.out.println("No pots crear més.");
            posicio = 9999;
        } else {
            while (!trobat && posicio < object.length) {

                if (object[posicio] == null) {
                    trobat = true;
                } else {
                    posicio++;
                }
            }
        }


        return posicio;
    }

    protected boolean afegirProgres(int credits, int estudiActual, int maxEstudi, Alumne alumne, String nomAssig) throws InterruptedException {
        boolean cursAcabat = false;

        if ((credits + estudiActual) <= maxEstudi) {
            System.out.println();
            System.out.println("Benvingut a " + nomAssig);
            System.out.println();
            System.out.print("Estudiant... ");

            for (int i = 0; i < 10; i++) {
                System.out.print("#");
                Thread.sleep(200);
            }

            estudiActual = estudiActual + credits;
            alumne.setProgres(estudiActual);
            System.out.println();
            System.out.println();
            System.out.println("Progres afegit :D");
        } else {
            System.out.println("No et queden crèdits suficients com per estudiar això.");
        }

        if (estudiActual >= 10) {
            cursAcabat = true;
        }

        return cursAcabat;
    }

    protected void mostrarInfoAlumne(Alumne alumne) {
        System.out.println();
        System.out.println("Nom:            " + alumne.getNom());
        System.out.println("Cognom:         " + alumne.getCognoms());
        System.out.println("NIF:            " + alumne.getNif());
        System.out.println("Matrícula:      " + alumne.getMatricula());
        System.out.println("Data neixement: " + alumne.getDataNeix());
        System.out.println("Progres:        " + alumne.getProgres() + "/10");
    }

    protected void mostrarInfoEscola(Escola escola) {
        Aula[] aules;
        Alumne[] alumnes = new Alumne[0];

        System.out.println();
        System.out.println("ESCOLA");
        System.out.println("<====>");
        System.out.println("Nom de l'escola: " + escola.getNom());
        System.out.println();

        aules = escola.getAules();

        for (int i = 0; i < aules.length; i++) {
            if (aules[i] != null) {
                System.out.println("AULA");
                System.out.println("<==>");
                System.out.println("=>Nom de l'aula:   " + aules[i].getNom());
                System.out.println("=>Màxim d'alumnes: " + aules[i].getMaxAlumnes());
                System.out.println();

                alumnes = aules[i].getAlumnes();
            }

            for (int j = 0; j < alumnes.length; j++) {
                if (alumnes[j] != null) {
                    System.out.println("ALUMNE");
                    System.out.println("<====>");
                    System.out.println("==>Nom de l'alumne:       " + alumnes[j].getNom());
                    System.out.println("==>Cognom de l'alumne:    " + alumnes[j].getCognoms());
                    System.out.println("==>NIF de l'alumne:       " + alumnes[j].getNif());
                    System.out.println("==>Matrícula de l'alumne: " + alumnes[j].getNom());
                    System.out.println("==>Data de naixement:     " + alumnes[j].getDataNeix());
                    System.out.println();
                }

            }
        }

    }
}
