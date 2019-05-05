public class DataModel {
    private String length;
    private String commonData;
    private String data;

    public DataModel(String length, String commonData, String data){
        this.length = String.format("%08d",Integer.parseInt(length));
        this.commonData = String.format("%s",commonData);
        this.data = data;
    }

    @Override
    public String toString() {
        return this.length+this.commonData+this.data;
    }


}
