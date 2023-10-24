
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalvarArquivo {

    public void grava(Object ob) {
        try {
            File arquivo = arquivo = new File("C:\\Users\\DISCENTE\\Documents\\NetBeansProjects\\TrabalhoEd02\\src\\arquivo.txt");
            ObjectOutputStream grava = new ObjectOutputStream(new FileOutputStream(arquivo));
            grava.writeObject(ob);
            grava.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object ler() {
        Object retorno = null;

        try {
            FileInputStream arquivo = new FileInputStream("C:\\Users\\DISCENTE\\Documents\\NetBeansProjects\\TrabalhoEd02\\src\\arquivo.txt");
            ObjectInputStream ler = new ObjectInputStream(arquivo);

            Object obj = (Object) ler.readObject();
            if (obj != null) {
                retorno = obj;
            }

            arquivo.close();
            ler.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }

        return retorno;
    }

    public String lerPatch() throws IOException {
        boolean imprimiu = false;
        String linha = "";
        String palavra = "";
        try {
            String endereco = "C:\\Users\\DISCENTE\\Documents\\NetBeansProjects\\TrabalhoEd02\\src\\patch.txt";
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(endereco)));
            while (true) {
                if (linha != null) {
                    palavra += linha;
                    imprimiu = true;
                } else {
                    break;
                }
                linha = buffRead.readLine();
            }
            buffRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imprimiu) {
            return palavra;
        } else {
            return null;
        }
    }
    
}
