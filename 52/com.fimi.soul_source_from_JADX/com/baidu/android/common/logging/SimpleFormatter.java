package com.baidu.android.common.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

class SimpleFormatter extends Formatter {
    private static String format;
    private Object[] args;
    Date dat;
    private MessageFormat formatter;

    static {
        format = "{0,date} {0,time}";
    }

    SimpleFormatter() {
        this.dat = new Date();
        this.args = new Object[1];
    }

    public synchronized String format(LogRecord logRecord) {
        StringBuffer stringBuffer;
        String str = null;
        synchronized (this) {
            String methodName;
            int lineNumber;
            Object obj = null;
            for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
                if (stackTraceElement.getClassName().startsWith(Log.class.getName())) {
                    obj = 1;
                } else if (obj != null) {
                    str = stackTraceElement.getClassName();
                    methodName = stackTraceElement.getMethodName();
                    lineNumber = stackTraceElement.getLineNumber();
                    break;
                }
            }
            lineNumber = 0;
            methodName = null;
            logRecord.setSourceClassName(str);
            logRecord.setSourceMethodName(methodName);
            stringBuffer = new StringBuffer();
            this.dat.setTime(logRecord.getMillis());
            this.args[0] = this.dat;
            StringBuffer stringBuffer2 = new StringBuffer();
            if (this.formatter == null) {
                this.formatter = new MessageFormat(format);
            }
            this.formatter.format(this.args, stringBuffer2, null);
            stringBuffer.append(stringBuffer2);
            stringBuffer.append("." + (logRecord.getMillis() % 1000));
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (logRecord.getSourceClassName() != null) {
                stringBuffer.append(logRecord.getSourceClassName());
            } else {
                stringBuffer.append(logRecord.getLoggerName());
            }
            if (logRecord.getSourceMethodName() != null) {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuffer.append(logRecord.getSourceMethodName());
            }
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer.append(lineNumber);
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            String formatMessage = formatMessage(logRecord);
            stringBuffer.append(logRecord.getLevel().getLocalizedName());
            stringBuffer.append(": ");
            stringBuffer.append(formatMessage);
            stringBuffer.append("\n");
            if (logRecord.getThrown() != null) {
                try {
                    Writer stringWriter = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    logRecord.getThrown().printStackTrace(printWriter);
                    printWriter.close();
                    stringBuffer.append(stringWriter.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }
}
