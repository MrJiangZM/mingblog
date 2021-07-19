webflux 响应式编程，基于netty非阻塞式I/O，相对较高难度的编码
   MVC更加方便，生态更加成熟活跃，也更方便调试，优先使用
   webflux在高并发场景会有一定的作用，对资源的消耗和要求更低
   
  public class Test {//模拟数据
  
  public static Student s1 = new Student("1", "zhangsan", "001");public static Student s2 = new Student("2", "lisi", "001");public static Student s3 = new Student("3", "wangwu", "001");public static Student s4 = new Student("4", "zhaoliu", "001");public static Student s5 = new Student("6", "tianqi", "001");public static Student s6 = new Student("6", "tianqi", "002");public static Student s7 = new Student("6", "tianqi", "003");public static Teacher t1 = new Teacher("001", "xiaoming", Subject.Math.getValue());public static Teacher t2 = new Teacher("002", "lihua", Subject.Music.getValue());public static Teacher t3 = new Teacher("003", "hanmeimei", Subject.Math.getValue());public static Teacher t4 = new Teacher("004", "lihua", Subject.English.getValue());public static List stus = new ArrayList<>();public static List teacs = new ArrayList<>();static{
  stus.add(s1);
  
  stus.add(s2);
  
  stus.add(s3);
  
  stus.add(s4);
  
  stus.add(s5);
  
  stus.add(s6);
  
  stus.add(s7);
  
  teacs.add(t1);
  
  teacs.add(t2);
  
  teacs.add(t3);
  
  teacs.add(t4);
  
  }public static voidmain(String[] args) {//找到所有数学老师的学生的家长的电话,并找他们开家长会
  
  List collect =teacs.stream()//过滤数学老师
  
  .filter(t ->Subject.Math.getValue().equals(t.getSubject()))//通过老师找学生
  
  .flatMap(t -> stus.stream().filter(s ->s.getTechId().equals(t.getId())))//过滤重复的学生(使用student的equals和hashCode方法)
  
  .distinct()//通过学生找家长(这里就简化为创建家长对象)
  
  .map(s ->{
  Parents p= newParents();
  
  p.setId(UUID.randomUUID().toString());
  
  p.setChirldId(s.getId());
  
  p.setName(s.getName().toUpperCase()+ "'s Parent");
  
  p.setEmail((int) (Math.random() * 1000000) + "@qq.com");returnp;
  
  })
  
  .collect(Collectors.toList());//打印到控制台看看
  
  collect.stream()
  
  .forEach(System.out::println);
  
  }
  
  }