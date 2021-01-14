from flask import Flask, redirect, render_template, request, session, url_for
import pandas as pd
import requests
from bs4 import BeautifulSoup as bs

# Flask Initialization
app = Flask(__name__)

# Functions Import
binfo = []


def one(search):    
    title = split(search)
    urlset = url_machine(imdb(title), title)
    return clean(google(urlset))


def split(search):
    target = ''
    target_list = search.split(' ')

    for i in range(len(target_list)):
        if i == len(target_list) - 1:
            target += target_list[i]
        else:
            target += target_list[i] + '+'
    
    return target
        
    
def clean(data):
    active = False

    #Ranking
    for i in range(0, len(data) - 1):
        cur = i

        for j in range(i + 1, len(data) - 1):
            if data[j][0] > data[cur][0]:
                cur = j
                active = True

        if (active):
            spare = data[i]
            data[i] = data[cur]
            data[cur] = spare
            active = False
            
    #Trimming
    for i in range(0, len(data) - 1):
        cur = i

        try:
            for j in range(i + 1, len(data) - 1):
                if data[j][1] == data[cur][1]:
                    data.pop(j)
        except:
            pass
    
    return data


def google(info):
    package = []
    
    for i in range(len(info)):
        seq_url = info[i][0]
        seq_weight = info[i][1]
        seq_page = requests.get(seq_url)
        seq_page.encoding = 'utf-8'
        soup = bs(seq_page.text, 'lxml')
        result = soup.find_all("div", class_="kCrYT")
        entry_rank = 0
        url = ''
        domain = ''
        sypnosis = ''

        for j in range(len(result)):
            try:
                url = result[j].select('a')[0].get('href').split('&')[0].split('=')[1]
                domain = url.split('/')[2]
            except:
                pass
            
            try:
                sypnosis = result[j].find_all('div', class_='BNeawe')[0].find_all('div', class_='BNeawe')[0].get_text()
            except:
                pass
            
            weight = round(seq_weight * (10 - entry_rank) / 10, 3)
            entry = [weight, domain, sypnosis, url]
            if j % 2 == 1:
                package.append(entry)
            else:
                entry_rank += 1
            
        package.append(entry)
        
    return package


def url_machine(info, title):
    booster = "+'film'or'movie'or'series'or'television'or'TV'"
    url_list = []
    
    for i in range(len(info)):
        credit = info[i][0]
        if credit == 'Director' or credit == 'Directors':
            credit_weight = 2
            weight_list = [1, 0.75, 0.65]
        elif credit == 'Writer' or credit == 'Writers':
            credit_weight = 1.75
            weight_list = [1, 0.85, 0.8]
        elif credit == 'Star' or credit == 'Stars':
            credit_weight = 1.66
            weight_list = [1, 0.9, 0.85]
        
        for j in range(len(info[i])):
            if j != 0:
                url = 'https://www.google.com/search?q=' + info[i][j] + '+' + title + booster
                url += "+-site:www.imdb.com" + "+-site:www.rottentomatoes.com" + "+-site:www.metacritic.com"
                url += "+-site:wikipedia.org" + "+-site:pinterest.com" + "+-site:www.amazon.com"
                url += "+-site:facebook.com" + "+-site:twitter.com" + "+-site:instagram.com"
                url += "+-site:youtube.com"
                url_list.append([url, credit_weight * weight_list[j - 1]])
    
    return url_list


def imdb(title):
    ban_list = ['crew', 'credit', 'credits']
    
    #Phase 0
    head = 'https://www.imdb.com/find?q='
    search_url = head + title
    get_page = requests.get(search_url)
    get_page.encoding = 'utf-8'
    search_soup = bs(get_page.text, 'lxml')
    result_id = search_soup.select('table')[0].select('tr')[0].select('a')[1].get('href')
    result_url = 'https://www.imdb.com' + result_id
    
    #Phase 1
    title_page = requests.get(result_url)
    title_page.encoding = 'utf-8'
    title_soup = bs(title_page.text, 'lxml')
    credits = title_soup.find_all("div", class_="credit_summary_item")
    
    credit_list = []
    for i in range(len(credits)):
        bclass_list = []
        class_list = []
        bclass_list.append(credits[i].select('h4')[0].get_text()[:-1])
        class_list.append(credits[i].select('h4')[0].get_text()[:-1])
        
        for j in range(len(credits[i].select('a'))):
            bentry = credits[i].select('a')[j].get_text()
            entry = credits[i].select('a')[j].get_text().split(' ')[-1]
            
            if bentry not in ban_list:
                bclass_list.append(bentry)

            if entry not in ban_list:
                class_list.append(entry)
        
        binfo.append(bclass_list)
        credit_list.append(class_list)
    
    return credit_list


@app.route("/")
def init():
    return render_template("main.html")

@app.route("/result", methods=["POST"])
def search():
    if request.method == "POST":
        search = request.values['search']
        data = one(search)
        print(binfo)
        return render_template("result.html", binfo=binfo, data=data)


@app.errorhandler(400) # Redirecting "Bad Request"
def bad_request(e):
    return redirect("/")


@app.errorhandler(404) # Redirecting undefined URLs
def page_not_found(e):
    return redirect("/")


@app.errorhandler(500) # Redirecting "Internal Server Error"
def internal_server_error(e):
    return redirect("/")


if __name__ == "__main__":
    app.run()