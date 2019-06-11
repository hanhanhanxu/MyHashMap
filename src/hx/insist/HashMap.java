package hx.insist;

public class HashMap<K,V> {
    class Entry<K,V>{
        private K k;
        private V v;
        private Entry<K,V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }

    private Entry<K,V>[] table;
    private static final Integer VOLUME = 10;//数组容量

    public HashMap(){
        table = new Entry[VOLUME];
    }
    public void put(K k,V v){
        //计算索引
       int index = getIndex(k);

        //此索引位置已经有了元素
        Entry entryTemp = table[index];
        while(entryTemp!=null){
            //key相同
            if ((entryTemp.getK()==null && k==null) || (entryTemp.getK().equals(k))){
                //System.out.println(index);
                //value使用新来的
                entryTemp.setV(v);
                return;
            }
            entryTemp = entryTemp.getNext();
        }
        //此索引位置没有元素 或 此索引位置有元素但是链表内没有重复key ：插入元素   头部添加元素  头插法
        //System.out.println(index);
        addEntry(k,v,index);
    }
    public V get(K k){
        int index = getIndex(k);

        Entry entryTemp = table[index];
        //此索引处元素个数>=1
        while(entryTemp!=null){
            if(entryTemp.getK().equals(k)){
                //如果有此key对应的元素一定会在这返回
                return (V) entryTemp.getV();
            }
            entryTemp = entryTemp.getNext();
        }
        //if (entryTemp==null || !entryTemp.getV().equals(k)){
        return null;//没有此元素返回null
    }
    private int getIndex(K k){
        //将字符串,数字,对象等转换为数字，使用官方的hash算法
        int hashCode = k.hashCode();
        //hash(散列)算法 得到索引
        int index = ((hashCode*2) >> 3) % table.length;
        return index;
    }
    private void addEntry(K k,V v,int index){
        Entry<K,V> entry = new Entry<>(k,v,table[index]);
        table[index] = entry;
    }

    @Override
    public String toString() {
        String str = "{";
        int index = 0;
        while(index<VOLUME){
            Entry<K,V> entryTemp = table[index];
            while(entryTemp!=null){
                str = str + entryTemp.getK()+"="+entryTemp.getV()+", ";
                //System.out.println(entryTemp.getK()+"="+entryTemp.getV()+",");
                entryTemp = entryTemp.getNext();
            }
            index++;
        }
        str = str.substring(0,str.length()-2) + "}";
        return  str;
    }
}
