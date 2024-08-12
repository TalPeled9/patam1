package test;

import java.util.HashMap;

public class DictionaryManager {
    private HashMap<String, Dictionary> dictionaries;
    private static DictionaryManager dictionaryManager = null;

    public boolean query(String... args){
        boolean found = false;
        for (int i=0 ; i<args.length - 1; i++) {
                if(!this.dictionaries.containsKey(args[i]))
                    this.dictionaries.put(args[i], new Dictionary(args[i]));
                if(this.dictionaries.get(args[i]).query(args[args.length - 1]))
                    found = true;
        }
        return found;
    }

    public boolean challenge(String... args){
        boolean found = false;
        for (int i=0 ; i<args.length - 1; i++) {
                if(!this.dictionaries.containsKey(args[i]))
                    this.dictionaries.put(args[i], new Dictionary(args[i]));
                if(this.dictionaries.get(args[i]).challenge(args[args.length - 1]))
                    found = true;
        }
        return found;
    }

    public int getSize(){
        return this.dictionaries.size();
    }

    private DictionaryManager(){
        this.dictionaries = new HashMap<String, Dictionary>();
    }

    public static DictionaryManager get(){
        if (dictionaryManager == null)
            dictionaryManager = new DictionaryManager();
        return dictionaryManager;
    }
}
