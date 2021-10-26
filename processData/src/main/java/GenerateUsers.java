import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateUsers {
    final String emailLexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final String nameLexicon = "abcdefghijklmnopqrstuvwxyz";
    final java.util.Random rand = new java.util.Random();


    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<String>();

    public String randomGenerate(String lexicon) {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public List<String> generateRandomEmail(int num){
        List<String> emailList = new ArrayList<>();
        while(emailList.size() < num){
            String pref = randomGenerate(emailLexicon);
            String email = pref + "@gmail.com";
            if(!emailList.contains(email)) emailList.add(email);
        }
        return emailList;
    }

    public List<String> generateRandomUserName(int num){
        List<String> userList = new ArrayList<>();
        while(userList.size() < num){
            String name = randomGenerate(emailLexicon);
            if(!userList.contains(name)) userList.add(name);
        }
        return userList;
    }

    public String generateRandomName(){
        String first = randomGenerate(nameLexicon);
        String last = randomGenerate(nameLexicon);
        return first + " " + last;
    }

    public static void main(String[] args) {
        GenerateUsers test = new GenerateUsers();

    }
}
