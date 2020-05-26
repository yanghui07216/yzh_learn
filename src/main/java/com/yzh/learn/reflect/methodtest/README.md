#Class
JVM为每个加载的class及interface创建了对应的Class实例来保存class及interface的所有信息；
获取一个class对应的Class实例后，就可以获取该class的所有信息；
通过Class实例获取class信息的方法称为反射（Reflection）；
JVM总是动态加载class，可以在运行期根据条件来控制加载class

#Field
Java的反射API提供的Field类封装了字段的所有信息：
通过Class实例的方法可以获取Field实例：getField()，getFields()，getDeclaredField()，getDeclaredFields()；
通过Field实例可以获取字段信息：getName()，getType()，getModifiers()；
通过Field实例可以读取或设置某个对象的字段，如果存在访问限制，要首先调用setAccessible(true)来访问非public字段。
通过反射读写字段是一种非常规方法，它会破坏对象的封装

# Method
Java的反射API提供的Method对象封装了方法的所有信息：
通过Class实例的方法可以获取Method实例：getMethod()，getMethods()，getDeclaredMethod()，getDeclaredMethods()；
通过Method实例可以获取方法信息：getName()，getReturnType()，getParameterTypes()，getModifiers()；
通过Method实例可以调用某个对象的方法：Object invoke(Object instance, Object... parameters)；
通过设置setAccessible(true)来访问非public方法；
通过反射调用方法时，仍然遵循多态原则。