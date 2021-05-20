package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        return textModifier(texto, false);
    }

    @Override
    public String descriptografar(String texto) {
    return textModifier(texto, true);
    }

    public String textModifier(String text, boolean mod) {
        text = text.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789 ";
        String codeAlphabet = "defghijklmnopqrstuvwxyzabc0123456789 ";
        String code = "";
        if(text == null){
            throw new NullPointerException();
        }else if (text == ""){
            throw new IllegalArgumentException();
        }else if (mod) {
            for (char i : text.toCharArray()) {
                code = code + (alphabet.charAt(codeAlphabet.indexOf(i)));
            }
        }else {
            for (char i : text.toCharArray()) {
                code = code + (codeAlphabet.charAt(alphabet.indexOf(i)));
            }
        }
        return code;
    }
}
