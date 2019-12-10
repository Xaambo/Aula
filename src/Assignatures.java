public class Assignatures {

    private String id;
    private String nom;
    private String nomGrm;
    private Assignatures[] assignatures = new Assignatures[5];

    public Assignatures() {

    }

    public Assignatures(String id, String nom, String nomgrm) {
        setId(id);
        setNom(nom);
        setNomGrm(nom);
    }

    public Assignatures(int numAssignatures) {

        String[] idAssig= {"1","2","3","4","5"};
        String[] nomAssig= {"programacio","fisica","matematiques", "eie", "tutoria"};
        String[] nomAssigGrm= {"programació","física","matemàtiques", "eie", "tutoria"};

        for (int i = 0; i < numAssignatures; i++) {
            assignatures[i] = new Assignatures(idAssig[i], nomAssig[i], nomAssigGrm[i]);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomGrm() {
        return nomGrm;
    }

    public void setNomGrm(String nomGrm) {
        this.nomGrm = nomGrm;
    }

    public Assignatures[] getAssignatures() {
        return assignatures;
    }
}
