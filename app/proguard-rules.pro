#-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志


-dontwarn com.handmark.pulltorefresh.library.**
-dontwarn com.loopj.android.http.**
-dontwarn org.greenrobot.eventbus.**
-dontwarn org.apache.http.**
-dontwarn android.net.http.**

-keepattributes Signature
#保持com.umeng.**这个包里面的所有类和所有方法不被混淆。(没有友盟的集成时删除此句)
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法
#忽略警告
-ignorewarning

-keep class com.handmark.pulltorefresh.library.**{*;}
-keep class com.loopj.android.http.**{*;}
-keep class org.greenrobot.eventbus.**{*;}

-keep class com.android.volley.** {*;}

-keep class com.android.volley.toolbox.** {*;}

-keep class com.android.volley.Response$* { *; }

-keep class com.android.volley.Request$* { *; }

-keep class com.android.volley.RequestQueue$* { *; }

-keep class com.android.volley.toolbox.HurlStack$* { *; }

-keep class com.android.volley.toolbox.ImageLoader$* { *; }
-keep class org.apache.http.** { *; }
-keep class android.net.http.** { *; }

-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}
#-keep class com.test.medicalsystem.commonclass.**{*;}
#-keep class android.util.Xml.**{*;}
#-keep class android.support.v7.widget.Toolbar{*;}
-keep class org.xmlpull.v1.** { *; }#这行代码不加会报java.lang.NoSuchMethodError: android.util.Xml.asAttributeSet


#-dontwarn javax.servlet.**
#-dontwarn org.joda.time.**
#-dontwarn org.w3c.dom.**

-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {  # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
 public static **[] values();
 public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
 public static final android.os.Parcelable$Creator *;
}
