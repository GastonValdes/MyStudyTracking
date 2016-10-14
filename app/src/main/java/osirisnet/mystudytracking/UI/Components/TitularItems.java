package osirisnet.mystudytracking.UI.Components;

/**
 * Created by gasto_000 on 30/9/2016.
 */

public class TitularItems {
    String mTitle; // Título del item
    String mDescription; // Descripción del item
    int mImg; // Imagen del ítem
    int mID;
    // Constructor por defecto de la clase
    public TitularItems(){}
    // Constructor con parámetros para inicializar el item
  //  public TitularItems( String _title, String _description, int _img){
public TitularItems( String _title, String _description, int _img, int _ID){ //este constructor era para obtener el ID

        mTitle = _title;
        mDescription = _description;
        mImg = _img;
        mID = _ID;
    }

        public TitularItems( String _title, String _description, int _img){ //este constructor era para obtener el ID

        mTitle = _title;
        mDescription = _description;
        mImg = _img;
    }
    // Aqui inicia el GET y el SET para cada propiedad de la clase
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    public int getImg() {
        return mImg;
    }
    public int getID(){
        return mID;
    }
    public void setImg(int img) {
        mImg = img;
    }
}
