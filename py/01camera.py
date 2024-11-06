import cv2

# 笔记本使用0
cap = cv2.VideoCapture(0)

while True:
    # ret 表示是否成功捕获到图形
    ret,img = cap.read()
    if ret:
        # img 为 左上角的名称
        cv2.imshow('img',img)
    #  监听按下q键  ord('q') 返回 'q' 的 ASCII 码
    if cv2.waitKey(1) == ord('q'):
        break