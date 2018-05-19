package nl.han.ica.oopg.logger;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class loggerTest {
    static Logger logger;

    @BeforeClass
    public static void setupClass()
    {
        logger = LogFactory.getLogger();
        logger.addLogHandler(new FileLogHandler());
    }

    @Test
    public void sameLoggerInstanceWhenNewCallToFactory()
    {
        assertEquals(LogFactory.getLogger(), logger);
    }

    @Test
    public void logHandlerHasBeenAddedTest()
    {
        boolean success = false;
        ArrayList<LogHandler> logHandlers = new ArrayList<LogHandler>();
        LogHandler logHandler = new FileLogHandler();
        logger.addLogHandler(logHandler);

        try {
            Field field = logger.getClass().getDeclaredField("logHandlers");
            logHandlers = (ArrayList)field.get(logger);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < logHandlers.size(); i++)
        {
            if (logHandlers.get(i).equals(logHandler))
            {
                success = true;
                break;
            }
        }
        assertTrue(success);
    }

    @Test
    public void logHandlerHasBeenRemovedTest()
    {
        boolean success = true;
        ArrayList<LogHandler> logHandlers = new ArrayList<LogHandler>();
        LogHandler logHandler = new FileLogHandler();
        logger.addLogHandler(logHandler);
        logger.removeLogHandler(logHandler);

        try {
            Field field = logger.getClass().getDeclaredField("logHandlers");
            logHandlers = (ArrayList)field.get(logger);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < logHandlers.size(); i++)
        {
            if (logHandlers.get(i).equals(logHandler))
            {
                success = false;
                break;
            }
        }
        assertTrue(success);
    }

    @Test
    public void logLNInvalidLogLevelReturnFalseTest()
    {
        assertFalse(logger.logln(5, "invalid loglevel"));
    }

    @Test
    public void logLNLogMessageToFileTest()
    {
        assertTrue(logger.logln(0, "test"));
    }

    @Test
    public void isValidLogLevel0ReturnTrue()
    {
        try {
            Method method = logger.getClass().getMethod("isValidLogLevel", new Class[]{int.class});
            assertTrue((boolean) method.invoke(logger, 0));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isValidLogLevel1ReturnTrue()
    {
        try {
            Method method = logger.getClass().getMethod("isValidLogLevel", new Class[]{int.class});
            assertTrue((boolean) method.invoke(logger, 1));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isValidLogLevel2ReturnTrue()
    {
        try {
            Method method = logger.getClass().getMethod("isValidLogLevel", new Class[]{int.class});
            assertTrue((boolean) method.invoke(logger, 2));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isValidLogLevel3ReturnTrue()
    {
        try {
            Method method = logger.getClass().getMethod("isValidLogLevel", new Class[]{int.class});
            assertTrue((boolean) method.invoke(logger, 3));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isValidLogLevel4ReturnTrue()
    {
        try {
            Method method = logger.getClass().getMethod("isValidLogLevel", new Class[]{int.class});
            assertTrue((boolean) method.invoke(logger, 4));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isValidLogLevel5OrGreaterReturnFalse()
    {
        try {
            Method method = logger.getClass().getMethod("isValidLogLevel", new Class[]{int.class});
            assertFalse((boolean) method.invoke(logger, 5));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
