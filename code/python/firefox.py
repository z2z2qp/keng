from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.firefox.options import Options
import time



profile = webdriver.FirefoxProfile()

profile.set_preference('permissions.default.image',1) ##不加载图片
profile.set_preference('dom.ipc.plugins.enabled.npswf32.dll',False) ##禁用flash
profile.set_preference('dom.ipc.plugins.enabled.libflashplayer.so',False) ##禁用flash

##无界面
opt = Options()
# opt.add_argument('--headless')
# opt.add_argument('--disable-gpu')
##创建火狐
browser = webdriver.Firefox(firefox_profile=profile,options=opt)
url = "https://ehire.51job.com/MainLogin.aspx"
browser.get(url)
# 点击、输入
browser.find_element_by_id("txtMemberNameCN").send_keys("东芯兰电子")
browser.find_element_by_id("txtUserNameCN").send_keys("东芯兰电子")
browser.find_element_by_id("txtPasswordCN").send_keys("QQ202528")
browser.find_element_by_id("Login_btnLoginCN").click() # 点击


time.sleep(10)#延时 2s 


#输出页面源码
# print(browser.page_source)
# print(browser.get_cookies())#cookie
# print(browser.current_url)#当前url

# browser.close()#关闭页面
#browser.quit()#关闭浏览器
time.sleep(10)#延时 2s 
# ret0 = browser.find_element_by_xpath("/html/body/form/div[4]/div[2]/div[1]/div[2]/div[4]/a")
# ret0.click()
# current = browser.window_handles#当前所以窗口
# browser.switch_to_window(current[1])
browser.get("https://ehire.51job.com/InboxResume/InboxRecentEngine.aspx?s=100")
browser.execute_script("window.scrollTo(0,document.body.scrollHeight)")
ret1 = browser.find_element_by_xpath("/html/body/form/div[2]/div/div[5]/div[2]/table")

time.sleep(10)
print(ret1.text)
